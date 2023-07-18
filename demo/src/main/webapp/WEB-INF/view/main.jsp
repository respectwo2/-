<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<div class="container-fluid">
	<!-- Row 1 -->
	<div class="row">
		<div class="align-items-stretch">
			<div class="card w-100">
				<div class="card-body">
					<div
						class="d-sm-flex d-block align-items-center justify-content-between mb-9">
						<div class="mb-3 mb-sm-0">
							<h5 class="card-title fw-semibold">Coin Price</h5>
							<ul class="nav nav-pills" role="tablist">
								<li class="nav-item" role="presentation"><a
									class="nav-link active" data-bs-toggle="tab" href="#navpill-1"
									role="tab" aria-selected="true"> <span class= "selected chart">선형 차트</span>
								</a></li>
								<li class="nav-item" role="presentation"><a
									class="nav-link" data-bs-toggle="tab" href="#navpill-2"
									role="tab" aria-selected="false" tabindex="-1"> <span class= "selected chart">캔들형 차트</span>
								</a></li>
							</ul>
							<div class="tab-content border mt-2">
								<div class="tab-pane p-3 active show" id="navpill-1"
									role="tabpanel">
									<script type="text/javascript"
										src="https://s3.tradingview.com/external-embedding/embed-widget-symbol-overview.js"
										async>
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
								<div class="tab-pane p-3" id="navpill-2" role="tabpanel">
									<!-- TradingView Widget BEGIN -->
									<div class="tradingview-widget-container">
										<div class="tradingview-widget-container__widget"></div>
										<div class="tradingview-widget-copyright">
											<a href="https://kr.tradingview.com/" rel="noopener nofollow"
												target="_blank"><span class="blue-text">트레이딩뷰에서
													모든 시장 추적</span></a>
										</div>
										<script type="text/javascript"
											src="https://s3.tradingview.com/external-embedding/embed-widget-symbol-overview.js"
											async>
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
  "chartType": "candlesticks",
  "maLineColor": "#2962FF",
  "maLineWidth": 1,
  "maLength": 9,
  "lineType": 0,
  "dateRanges": [
    "1d|1",
    "1m|30",
    "3m|60",
    "12m|1D",
    "60m|1W",
    "all|1M"
  ],
  "upColor": "#22ab94",
  "downColor": "#f7525f",
  "borderUpColor": "#22ab94",
  "borderDownColor": "#f7525f",
  "wickUpColor": "#22ab94",
  "wickDownColor": "#f7525f"
}
  </script>
									</div>
									<!-- TradingView Widget END -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="row">
					<div class="col-lg-12"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-4 d-flex align-items-stretch">
			<div class="card w-100">
				<div class="card-body p-4">
					<div class="mb-4">

						<h5 class="card-title fw-semibold">Price Difference</h5>
						<br>					
						<a>바이낸스 가격</a>
						<h4 id="binancePrice"></h4><br>
						<h5>업비트 가격이 바이낸스 가격보다 </h5>
						<h4 id="kimp"></h4>
						<h5>높게 형성되어 있어요</h5>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-8 d-flex align-items-stretch">
			<div class="card w-100">
				<div class="card-body p-4">
					<h5 class="card-title fw-semibold mb-4">Recent Transactions</h5>
					<div class="row mt-4">
						<div class="col-md-6">
							<div class="hstack p-3 border rounded mb-3 mb-md-0">
								<div class="ms-3">
									<h6 class="mb-0 fs-3">고가</h6>
									<span id="high_price" class="fs-2" ></span>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="hstack p-3 border rounded">
								<div class="ms-3">
									<h6 class="mb-0 fs-3">저가</h6>
									 <span class="fs-2" id = "low_price">
										</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="container-fluid">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title fw-semibold mb-4">Coin Info</h5>
						<div class="card">
							<div class="card-body p-4">
								<p id="info">${info}</p>
							</div>
						</div>
						<h5 class="card-title fw-semibold mb-4">Coin Issuer</h5>
						<div class="card mb-0">
							<div class="card-body p-4">
								<h5 id="info">${issuer}</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="layout/footer.jsp"%>

<script type="text/javascript">
const tickerValue = "${ticker}";
</script>
<script src="../js/kimchiPremium.js"></script>
<script>
  $(document).ready(function () {
    $("#searchForm").submit(function (e) {
      e.preventDefault();

      var searchTerm = $("#searchInput").val();
      var url = "/main?tvwidgetsymbol=" + searchTerm;
      window.location.href = url;
    });
  });
</script>

