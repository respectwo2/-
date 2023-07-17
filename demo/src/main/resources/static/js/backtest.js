/**
 * 
 */
(async () => {	

    const data = await fetch(
        'http://localhost8080/backtest/1'
    ).then(response => response.json());


    // create the chart
    Highcharts.stockChart('container2', {
        rangeSelector: {
            selected: 1
        },

        title: {
            text: 'AAPL Stock Price'
        },

        series: [{
            type: 'candlestick',
            name: 'AAPL Stock Price',
            data: data,
            dataGrouping: {
                units: [
                    [
                        'week', // unit name
                        [1] // allowed multiples
                    ], [
                        'month',
                        [1, 2, 3, 4, 6]
                    ]
                ]
            }
        }]
    });
    
})();