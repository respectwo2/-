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
									role="tab" aria-selected="true"> <span>Tab 1</span>
								</a></li>
								<li class="nav-item" role="presentation"><a
									class="nav-link" data-bs-toggle="tab" href="#navpill-2"
									role="tab" aria-selected="false" tabindex="-1"> <span>Tab
											2</span>
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
						<h5 class="card-title fw-semibold">Recent Transactions</h5>					
						<h6 id="binancePrice"></h6>
						<h5 id="kimp"></h5>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-8 d-flex align-items-stretch">
			<div class="card w-100">
				<div class="card-body p-4">
					<h5 class="card-title fw-semibold mb-4">Recent Transactions</h5>
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
								<p id="info">${issuer}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="layout/footer.jsp"%>
<
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

