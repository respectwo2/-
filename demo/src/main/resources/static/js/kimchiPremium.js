$(function() {
	console.log("AA")
	// 환율 가져오기: basePrice가 환율이라고 굳게 믿고 있음.
	var request = new XMLHttpRequest();
	request.open('GET', 'https://quotation-api-cdn.dunamu.com/v1/forex/recent?codes=FRX.KRWUSD');
	request.send();
	var rate = 0;
	request.onload = function() {
		rate = JSON.parse(request.response)[0].basePrice;
	};

	const tickerLowerCase = tickerValue.toLowerCase();//바이낸스에서 소문자로 검색해서. 티커 소문자 변환
	console.log(tickerLowerCase);
	// 바이낸스 코인 가격 websocket api 
	const binaceWebSocket = new WebSocket('wss://stream.binance.com:9443/ws/' + tickerLowerCase + 'usdt@trade');
	binaceWebSocket.onopen = () => { };
	binaceWebSocket.onmessage = (event) => {
		const data = JSON.parse(event.data);
		const price = data.p * rate; // Current price 
		document.getElementById('binance_price').textContent = price + '$';
	};

	// Event handler for WebSocket errors
	binaceWebSocket.onerror = (error) => {
		console.error(`WebSocket error: ${error}`);
	};

	// Event handler for WebSocket connection close
	binaceWebSocket.onclose = () => {
		console.log(`Connection closed for ${symbol}`);
	};

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
			const upbitPrice = jsonData.trade_price;
			document.getElementById('price').textContent = upbitPrice + '₩';

			// 김프 계산: 100 - (바이낸스/한국 가격)*100
			// 0%보다 크면 초록색, 작으면 빨간색 폰트
			const binancePrice = document.getElementById('binance_price').textContent;
			const kimchi = ((upbitPrice - binancePrice) / binancePrice * 100).toFixed(2);

			if (kimchi >= 0) document.getElementById("kimp").style.color = 'green';
			else document.getElementById("kimp").style.color = 'red';

			document.getElementById("kimp").textContent = kimchi + '%';


			document.getElementById("change_rate").textContent = jsonData.signed_change_rate * 100 + '%';
		};

		reader.readAsText(blob);
	};
})