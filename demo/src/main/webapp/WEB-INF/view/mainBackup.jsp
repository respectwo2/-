<!-- main.jsp -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Landing Page</title>
      <link rel="shortcut icon" type="image/png" href="../images/logos/favicon.png" />
  <link rel="stylesheet" href="../css/styles.min.css" />
    <style>
        #mainContent {
            float: right;
            width: 70%;
        }
        
        #sidebar {
            float: left;
            width: 30%;
        }
        
        #searchbar{
        	float:left;
        	width: 30%;
        }
    </style>
</head>
<body>
	<div id="topbar">
	<%@ include file="topbar.jsp" %>
    </div>
     <div id="searchbar">
    	<%@ include file="index/index.jsp" %>
    </div>
    <div id="charst">
    	<%@ include file="charts.jsp" %>
    </div> 
   
    
    <div id="mainContent">
<!-- TradingView Widget BEGIN -->
<div class="tradingview-widget-container">
  <div class="tradingview-widget-container__widget"></div>
  <div class="tradingview-widget-copyright"><a href="https://kr.tradingview.com/" rel="noopener nofollow" target="_blank"></a></div>
  <script type="text/javascript" src="https://s3.tradingview.com/external-embedding/embed-widget-symbol-overview.js" async>
  {
  "symbols": [
    [
      "UPBIT:${ticker}KRW|1D"
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
</div>
<nav class="sidebar-nav scroll-sidebar" data-simplebar="init">
        <%@ include file="sidebar.jsp" %>
    </nav> 

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="../libs/jquery/dist/jquery.min.js"></script>
  <script src="../libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <script src="../js/sidebarmenu.js"></script>
  <script src="../js/app.min.js"></script>
  <script src="../libs/apexcharts/dist/apexcharts.min.js"></script>
  <script src="../libs/simplebar/dist/simplebar.js"></script>
  <script src="../js/dashboard.js"></script>
    
</body>
</html>
