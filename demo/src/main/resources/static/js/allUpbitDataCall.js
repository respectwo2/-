const allUpbitWebSocket = new WebSocket('wss://api.upbit.com/websocket/v1');
    const tickerList = [
      'KRW-AAVE', 'KRW-ALGO', 'KRW-APT', 'KRW-ATOM', 'KRW-AVAX', 'KRW-BCH', 'KRW-BTC', 'KRW-CRO',
      'KRW-DOGE', 'KRW-DOT', 'KRW-EOS', 'KRW-ETC', 'KRW-ETH', 'KRW-GRS', 'KRW-HBAR', 'KRW-HIFI',
      'KRW-KAVA', 'KRW-LINK', 'KRW-MATIC', 'KRW-MBL', 'KRW-MTL', 'KRW-NEAR', 'KRW-SAND', 'KRW-SHIB',
      'KRW-SOL', 'KRW-STX', 'KRW-T', 'KRW-TRX', 'KRW-VET', 'KRW-WAVES', 'KRW-XLM', 'KRW-XRP'
    ];

    // Get the table body
    const tableBody = document.getElementById('marketData');

    // Create rows for each ticker in the ticker list
    for (let i = 0; i < tickerList.length; i++) {
      const newRow = document.createElement('tr');

      // Create Market column and set its value
      const marketCell = document.createElement('td');
      marketCell.textContent = tickerList[i].substr(4);
      newRow.appendChild(marketCell);
      
      for (let j = 0; j < 5; j++) {
          const cell = document.createElement('td');
          cell.textContent = '-';
          newRow.appendChild(cell);
        }

      // Add the new row to the table body using the tickerList index as the key
      tableBody.appendChild(newRow);
    }

    // WebSocket onopen event handler
    allUpbitWebSocket.onopen = () => {
      const subscriptionMessage = JSON.stringify([{ ticket: 'test' }, { type: 'ticker', codes: tickerList }]);
      allUpbitWebSocket.send(subscriptionMessage);
    };

    // WebSocket onmessage event handler
    allUpbitWebSocket.onmessage = function(event) {
      const blob = event.data;
      const reader = new FileReader();


      reader.onload = function(event) {
        const textData = event.target.result;
        const tickerData = JSON.parse(textData);

        // Find the corresponding row in the table based on the ticker market
        var market = tickerData.code;
        market = market.substr(4);

        const rows = tableBody.getElementsByTagName('tr');

        for (let i = 0; i < rows.length; i++) {
          const row = rows[i];
          const marketCell = row.cells[0];
          if (marketCell && marketCell.textContent === market) {
        	  
            // Update the Trade Price, Signed Change Rate, Highest 52-Week Price, and Lowest 52-Week Price columns
            row.cells[1].textContent = tickerData.trade_price;
            row.cells[2].textContent = tickerData.prev_closing_price;
            tickerData.signed_change_rate =   (tickerData.signed_change_rate * 100).toFixed(2);
            if (tickerData.signed_change_rate >= 0) row.cells[3].style.color = 'green'
			else row.cells[3].style.color = 'red'
			row.cells[3].textContent = tickerData.signed_change_rate + '%';
            row.cells[4].textContent = tickerData.highest_52_week_price;
            row.cells[5].textContent = tickerData.lowest_52_week_price;
            
            break; // Exit the loop after finding the matching row
          }
        }
      };

      reader.readAsText(blob);
    };

