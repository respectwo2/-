<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.0.js"
	integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
	crossorigin="anonymous"></script>
</head>
<p>한국 가격</p>
<p id="price"></p>
<p>바이낸스 가격</p>
<p id="binance_price"></p>
<p>김프</p>
<p id="kimp"></p>
<p>등락률</p>
<p id="change_rate"></p>

<!-- TradingView Widget BEGIN -->
<div class="tradingview-widget-container">
	<div class="tradingview-widget-container__widget"></div>
	<div class="tradingview-widget-copyright">
		<a href="https://kr.tradingview.com/" rel="noopener nofollow"
			target="_blank"> <span class="blue-text">트레이딩뷰에서 모든 시장 추적</span>
		</a>
	</div>
	<script type="text/javascript"
		src="https://s3.tradingview.com/external-embedding/embed-widget-symbol-overview.js"
		async>
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

    
  </script>

</body>
</html>
