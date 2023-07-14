<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title></title>
  <script src="https://code.highcharts.com/highcharts.js"></script>
  <script src="https://code.highcharts.com/modules/stock.js"></script>
</head>
<body>
  <div id="chart-container">
  <script>
  (async () => {
    const response = await fetch('/chartsJS');
    const data = await response.json();

    Highcharts.chart('chart-container', {
        chart: {
            zoomType: 'x'
        },
        title: {
            text: 'UBAI & UBMI rate',
            align: 'left'
        },
        xAxis: {
            type: 'datetime',
            categories: data.ubaiData.map(dataPoint => dataPoint[0]) // 수정: UBAI 데이터의 candleTimeDate 사용
        },
        yAxis: {
            title: {
                text: 'Trade Price'
            }
        },
        legend: {
            enabled: true
        },
        plotOptions: {
            area: {
                fillColor: {
                    linearGradient: {
                        x1: 0,
                        y1: 0,
                        x2: 0,
                        y2: 1
                    },
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                },
                marker: {
                    radius: 2
                },
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                threshold: null
            }
        },
        series: [{
            type: 'area',
            name: 'UBAI',
            data: data.ubaiData.map(dataPoint => dataPoint[1]) // 수정: UBAI 데이터의 tradePrice 사용
        }, {
            type: 'area',
            name: 'UBMI',
            data: data.ubmiData.map(dataPoint => dataPoint[1]) // 수정: UBMI 데이터의 tradePrice 사용
        }]
    });
  })();
  </script>
  </div>
</body>
</html>