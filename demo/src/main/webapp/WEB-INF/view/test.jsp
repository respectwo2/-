<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- TradingView Widget BEGIN -->
<div class="tradingview-widget-container">
  <div id="basic-area-chart-demo"></div>
  <div class="tradingview-widget-copyright"><a href="https://kr.tradingview.com/" rel="noopener nofollow" target="_blank"><span class="blue-text">트레이딩뷰에서 모든 시장 추적</span></a></div>
  <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
  <script type="text/javascript">
  new TradingView.widget(
  {
  "container_id": "basic-area-chart-demo",
  "width": "100%",
  "height": "100%",
  "autosize": true,
  "symbol": "UPBIT:STXKRW",
  "interval": "D",
  "timezone": "exchange",
  "theme": "light",
  "style": "3",
  "toolbar_bg": "#f1f3f6",
  "hide_top_toolbar": true,
  "save_image": false,
  "locale": "kr"
}
  );
  </script>
</div>
<!-- TradingView Widget END -->
</body>
</html>