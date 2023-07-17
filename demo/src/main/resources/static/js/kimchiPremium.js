$(function() {
	let binancePrice = null;
	let upbitPrice = null;
	var rate = 0;

	// 환율 가져오기: basePrice가 환율이라고 굳게 믿고 있음.
	var request = new XMLHttpRequest();
	request.open('GET', 'https://quotation-api-cdn.dunamu.com/v1/forex/recent?codes=FRX.KRWUSD');
	request.send();
	request.onload = function() {
		rate = JSON.parse(request.response)[0].basePrice;
	};

	const tickerLowerCase = tickerValue.toLowerCase();//바이낸스에서 소문자로 검색해서. 티커 소문자 변환

	// 바이낸스 코인 가격 websocket api 
	const binaceWebSocket = new WebSocket('wss://stream.binance.com:9443/ws/' + tickerLowerCase + 'usdt@trade');
	binaceWebSocket.onopen = () => { };
	binaceWebSocket.onmessage = (event) => {
		const data = JSON.parse(event.data);
		const price = data.p * 1;
		document.getElementById("binancePrice").textContent = price.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '$';
		binancePrice = price * rate; // Current price 
		calculation();
	};

	binaceWebSocket.onerror = (error) => {
		console.error(`WebSocket error: ${error}`);
	};

	binaceWebSocket.onclose = () => {
		console.log(`Connection closed for ${symbol}`);
	};

	// upbit 코인 계산
	// upbit websocket api end point: wss://api.upbit.com/websocket/v1
	const upbitWebSocket = new WebSocket('wss://api.upbit.com/websocket/v1');
	upbitWebSocket.onopen = () => {
		upbitWebSocket.send('[{"ticket":"test"},{"type":"ticker","codes":["KRW-' + tickerValue + '"]}]');
	};


	// Event handler for when a message is received
	upbitWebSocket.onmessage = function(event) {
		const blob = event.data;
		const reader = new FileReader();

		// 데이터 파싱
		reader.onload = function(event) {
			const textData = event.target.result;
			const jsonData = JSON.parse(textData);

			// 업비트 가격
			const price = jsonData.trade_price;
			upbitPrice = price;

			const high_price = jsonData.high_price + "";
			const low_price = jsonData.low_price + "";
			console.log(high_price)
			document.getElementById("high_price").textContent = high_price.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			document.getElementById("low_price").textContent = low_price.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			document.getElementById("high_price").style.color = 'green';
			document.getElementById("low_price").style.color = 'red';



			calculation();
		};

		reader.readAsText(blob);
	};

	const calculation = function() {
		if (upbitPrice && binancePrice) {
			const kimchiPremium = ((upbitPrice - binancePrice) / binancePrice * 100).toFixed(2);
			if (kimchiPremium >= 0) document.getElementById("kimp").style.color = 'green';
			else document.getElementById("kimp").style.color = 'red';
			document.getElementById("kimp").textContent = kimchiPremium + '%';
		}
	};
})