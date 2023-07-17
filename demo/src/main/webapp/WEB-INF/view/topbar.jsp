<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- TradingView Widget BEGIN -->
<!-- TradingView Widget BEGIN -->
<div class="tradingview-widget-container">
  <div class="tradingview-widget-container__widget"></div>
  <div class="tradingview-widget-copyright"><a href="https://kr.tradingview.com/" rel="noopener nofollow" target="_blank"></a></div>
  <script type="text/javascript" src="https://s3.tradingview.com/external-embedding/embed-widget-tickers.js" async>
  {
  "symbols": [
    {
      "description": "비트코인",
      "proName": "UPBIT:BTCKRW"
    },
    {
      "description": "이더리움",
      "proName": "UPBIT:ETHKRW"
    },
    {
      "description": "메탈",
      "proName": "UPBIT:MTLKRW"
    },
    {
      "description": "도지코인",
      "proName": "UPBIT:DOGEKRW"
    },
    {
      "description": "리플",
      "proName": "UPBIT:XRPKRW"
    },
    {
      "description": "스택스",
      "proName": "UPBIT:STXKRW"
    },
    {
      "description": "에이브",
      "proName": "UPBIT:AAVEKRW"
    },
    {
      "description": "퀀텀",
      "proName": "UPBIT:QTUMKRW"
    }
  ],
  "colorTheme": "light",
  "isTransparent": false,
  "showSymbolLogo": true,
  "largeChartUrl": "http://localhost:8080/main",
  "locale": "kr"
}
  </script>
</div>
<!-- TradingView Widget END -->
</body>
</html>