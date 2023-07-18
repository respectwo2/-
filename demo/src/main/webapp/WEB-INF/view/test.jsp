<!DOCTYPE html>
<html>
<head>
  <title>Upbit WebSocket API Example</title>
  <script>
    const coinData = []; // Array to store the received ticker data

    function subscribeToTickers() {
      const socket = new WebSocket('wss://api.upbit.com/websocket/v1');
      
      const message = [
        { "ticket": "UNIQUE_TICKET" },
        { "type": "ticker", "codes": ["KRW-BTC", "KRW-ETH", "KRW-XRP", /* Add more coin codes here */] }
      ];
      
      socket.addEventListener('open', function() {
        socket.send(JSON.stringify(message));
      });
      
      socket.addEventListener('message', function(event) {
        const blob = event.data;
        const reader = new FileReader();
        
        reader.addEventListener('loadend', function() {
          const data = JSON.parse(reader.result);
          console.log(data); // Print all the data from the WebSocket response
          if (data.type === 'ticker') {
            coinData.push(data); // Store the received ticker data in the array
            displayCoinData(); // Display the tabulated and sorted data
          }
        });
        
        reader.readAsText(blob);
      });
    }

    function displayCoinData() {
      // Sort the data based on trade_price in ascending order
      const sortedData = coinData.sort((a, b) => a.trade_price - b.trade_price);

      const tableRows = sortedData.map(({ code, trade_price, change }) => {
        return `<tr><td>${code}</td><td>${trade_price}</td><td>${change}</td></tr>`;
      });

      const coinDataElement = document.getElementById('coin-data');
      coinDataElement.innerHTML = `
        <table>
          <tr>
            <th>Market</th>
            <th>Trade Price</th>
            <th>Change Price</th>
          </tr>
          ${tableRows.join('')}
        </table>
      `;
    }
  </script>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
    }
    th, td {
      border: 1px solid black;
      padding: 8px;
      text-align: left;
    }
  </style>
</head>
<body>
  <h1>Upbit WebSocket API Example</h1>
  <div id="coin-data"></div>

  <script>
    subscribeToTickers();
  </script>
</body>
</html>
