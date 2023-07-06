<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
</head>
<body>
  <table id="kimchiTable">
    <thead>
      <tr>
        <th>Coin</th>
        <th>Upbit Price</th>
        <th>Binance Price</th>
        <th>Kimchi Premium</th>
      </tr>
    </thead>
    <tbody></tbody>
  </table>
  <script>
    var request = new XMLHttpRequest();
    request.open('GET', 'https://quotation-api-cdn.dunamu.com/v1/forex/recent?codes=FRX.KRWUSD');
    request.send();
    var rate = 0;
    request.onload = function() {
      rate = JSON.parse(request.response)[0].basePrice;

      var upbitRequest = new XMLHttpRequest();
      upbitRequest.open('GET', 'https://api.upbit.com/v1/market/all');
      upbitRequest.send();
      upbitRequest.onload = function() {
        var coins = JSON.parse(upbitRequest.response);

        var tableBody = document.getElementById('kimchiTable').getElementsByTagName('tbody')[0];

        coins.forEach(function(coin) {
          if (coin.market.startsWith('KRW')) {
            var row = tableBody.insertRow();
            var coinCell = row.insertCell(0);
            var upbitPriceCell = row.insertCell(1);
            var binancePriceCell = row.insertCell(2);
            var kimchiCell = row.insertCell(3);

            coinCell.innerHTML = coin.market;
            upbitPriceCell.setAttribute('id', 'upbitPrice_' + coin.market);
            binancePriceCell.setAttribute('id', 'binancePrice_' + coin.market);
            kimchiCell.setAttribute('id', 'kimchiPremium_' + coin.market);

            var upbitWebSocket = new WebSocket('wss://api.upbit.com/websocket/v1');
            upbitWebSocket.onopen = function() {
              upbitWebSocket.send('[{"ticket":"test"},{"type":"ticker","codes":["' + coin.market + '"]}]');
            };

            upbitWebSocket.onmessage = function(event) {
              var blob = event.data;
              var reader = new FileReader();

              reader.onload = function(event) {
                var textData = event.target.result;
                var jsonData = JSON.parse(textData);

                var upbitPrice = jsonData.trade_price;
                document.getElementById('upbitPrice_' + coin.market).textContent = upbitPrice;

                var binanceWebSocket = new WebSocket('wss://stream.binance.com:9443/ws/' + coin.market.replace('KRW', '').toLowerCase() + 'usdt@trade');
                binanceWebSocket.onopen = function() {};
                binanceWebSocket.onmessage = function(event) {
                  var data = JSON.parse(event.data);
                  var price = data.p * rate;
                  document.getElementById('binancePrice_' + coin.market).textContent = price;

                  var kimchiPremium = ((upbitPrice - price) / price * 100).toFixed(2);
                  if (kimchiPremium >= 0) {
                    document.getElementById('kimchiPremium_' + coin.market).style.color = 'green';
                  } else {
                    document.getElementById('kimchiPremium_' + coin.market).style.color = 'red';
                  }
                  document.getElementById('kimchiPremium_' + coin.market).textContent = kimchiPremium + '%';
                };

                binanceWebSocket.onerror = function(error) {
                  console.error('WebSocket error: ' + error);
                };

                binanceWebSocket.onclose = function() {
                  console.log('Connection closed for ' + coin.market);
                };
              };

              reader.readAsText(blob);
            };
          }
        });
      };
    };
  </script>
</body>
</html>
