<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
</head>
<p> 한국 가격 </p>
  <p id="price"></p>
  <p> 바이낸스 가격</p>
  <p id="binance_price"></p>
  <p> 김프 </p>
  <p id="kimp"></p>

  <!-- TradingView Widget BEGIN -->
  <div class="tradingview-widget-container">
    <div class="tradingview-widget-container__widget"></div>
    <div class="tradingview-widget-copyright">
      <a href="https://kr.tradingview.com/" rel="noopener nofollow" target="_blank">
        <span class="blue-text">트레이딩뷰에서 모든 시장 추적</span>
      </a>
    </div>
    <script type="text/javascript" src="https://s3.tradingview.com/external-embedding/embed-widget-symbol-overview.js" async>
    {
      "symbols": [
        [
          "UPBIT:${ticker}KRW|ALL"
        ]
      ],
      "chartOnly": false,
      "width": 1000,
      "height": 500,
      "locale": "kr",
      "colorTheme": "light",
      "autosize": false,
      "showVolume": false,
      "showMA": false,
      "hideDateRanges": false,
      "hideMarketStatus": false,
      "hideSymbolLogo": false,
      "scalePosition": "right",
      "scaleMode": "Normal",
      "fontFamily": "-apple-system, BlinkMacSystemFont, Trebuchet MS, Roboto, Ubuntu, sans-serif",
      "fontSize": "10",
      "noTimeScale": false,
      "valuesTracking": "1",
      "changeMode": "price-and-percent",
      "chartType": "area",
      "maLineColor": "#2962FF",
      "maLineWidth": 1,
      "maLength": 9,
      "lineWidth": 2,
      "lineType": 0,
      "dateRanges": [
        "1d|1",
        "1m|30",
        "3m|60",
        "12m|1D",
        "60m|1W",
        "all|1M"
      ]
    }
    </script>
  </div>
  <!-- TradingView Widget END -->

  <script>
    // 환율 가져오기: basePrice가 환율이라고 굳게 믿고 있음.
    var request = new XMLHttpRequest();
    request.open('GET', 'https://quotation-api-cdn.dunamu.com/v1/forex/recent?codes=FRX.KRWUSD');
    request.send();
    var rate = 0;
    request.onload = function() {
      rate = JSON.parse(request.response)[0].basePrice;
    };

    const tickerLowerCase = "${ticker}".toLowerCase(); //바이낸스에서 소문자로 검색해서. 티커 소문자 변환
    
    // 바이낸스 코인 가격 websocket api 
    const ws = new WebSocket('wss://stream.binance.com:9443/ws/'+tickerLowerCase +'usdt@trade');
    ws.onopen = () => {};
    ws.onmessage = (event) => {
      const data = JSON.parse(event.data);
      const price = data.p * rate; // Current price 
      console.log(price);
      document.getElementById('binance_price').textContent = price;
    };

    // Event handler for WebSocket errors
    ws.onerror = (error) => {
      console.error(`WebSocket error: ${error}`);
    };

    // Event handler for WebSocket connection close
    ws.onclose = () => {
      console.log(`Connection closed for ${symbol}`);
    };
  </script>

  <script>
    // upbit websocket api end point: wss://api.upbit.com/websocket/v1
    const socket = new WebSocket('wss://api.upbit.com/websocket/v1');
    const code = "${ticker}"; // 티커값 가져옴. 코드 ex: KRW-BTC, 
    socket.onopen = () => {
      socket.send('[{"ticket":"test"},{"type":"ticker","codes":["KRW-${ticker}"]}]');
    };

    // Event handler for when a message is received
    socket.onmessage = function(event) {
      const blob = event.data;
      const reader = new FileReader();
	
      // 데이터 파싱
      reader.onload = function(event) {
        const textData = event.target.result;
        const jsonData = JSON.parse(textData);

        // 업비트 가격
        const trade_price = jsonData.trade_price;
        document.getElementById('price').textContent = trade_price
        
        // 김프 계산: 100 - (바이낸스/한국 가격)*100
        // 0%보다 크면 초록색, 작으면 빨간색 폰트
        const binance_price = document.getElementById('price').textContent;
        const kimchi = (100 - (binance_price / trade_price) * 100).toFixed(2);

        if (kimchi >= 0) document.getElementById("kimp").style.color = 'green';
        else document.getElementById("kimp").style.color = 'red';

        document.getElementById("kimp").textContent = kimchi + '%';
      };

      reader.readAsText(blob);
    };
    
    window.addEventListener('DOMContentLoaded', function() {
    	  // Access the TradingView widget
    	  const widget = new TradingView.widget({
    	    // Widget configuration options...
    	    symbol: 'UPBIT:BTCKRW|ALL',
    	    // Other options...
    	  });

    	  // Get the value from the TradingView widget
    	  const value = widget.activeChart().model().mainSeriesPriceLast();
    	  console.log(value); // Log the value to the console
    	});
  </script>
</body>
</html>
