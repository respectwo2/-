<%@ include file="layout/header.jsp"%>
<div class="container-fluid">
	<!--  Row 1 -->
	<div class="row">
		<div class="align-items-strech">
			<div class="card w-100">
				<div class="card-body">
					<div
						class="d-sm-flex d-block align-items-center justify-content-between mb-9">
						<div class="mb-3 mb-sm-0">
							<h5 class="card-title fw-semibold">Coin Price</h5>
							<div class="tradingview-widget-container__widget"></div>
							<div class="tradingview-widget-copyright">
								<a href="https://kr.tradingview.com/" rel="noopener nofollow"
									target="_blank"></a>
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
					</div>

				</div>
			</div>
			<div class="col-lg-4">
				<div class="row">
					<div class="col-lg-12"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 d-flex align-items-stretch">
				<div class="card w-100">
					<div class="card-body p-4">
						<div class="mb-4">
							<h5 class="card-title fw-semibold">Recent Transactions</h5>
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
</div>
</div>
<%@ include file="layout/footer.jsp"%>
<script>
$(document).ready(function() {
    $("#searchForm").submit(function(e) {
        e.preventDefault(); 

        var searchTerm = $("#searchInput").val();
        var url = "/main?tvwidgetsymbol=" + searchTerm;
        window.location.href = url;
    });
});
</script>