<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<body>
	<script src="http://code.highcharts.com/stock/highstock.js"></script>
	<script src="http://code.highcharts.com/stock/modules/exporting.js"></script>
	<script src="http://code.highcharts.com/stock/modules/accessibility.js"></script>

	<!-- Include the library in the page -->
	<script src="http://unpkg.com/apollo-client-browser@1.9.0"></script>

	<div class="container-fluid">
		<div class="card">
			<div class="card-body">
				<h5 class="card-title fw-semibold mb-4">UBMI & UBAI Rate</h5>
				<%@ include file="charts.jsp"%>
				<div>
					<p class="mb-0"></p>
					<h5 id="indexinfo">UBMI (UPBIT Market Index)</h5>
					<br> <span>유동시가총액을 사용한 시가총액 가중 방식 인덱스</span><br> <span>거래소에
						상장된 모든 화폐를 계산해 시장 전체의 흐름을 파악할 수 있는 지표</span><br>
					<br> <span></span> <span></span>
					<h5 id="indexinfo">UBAI (UPBIT Altcoin Index)</h5>
					<br> <span>비트코인을 제외한 UBMI 인덱스</span><br> <span>비트코인의
						지배적인 영향력을 제외한 알트코인의 움직임을 알 수 있는 지표</span><br>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="card">
			<div class="card-body">
				<h5 class="card-title fw-semibold mb-4">MVRV Rate</h5>
				<script type="text/javascript" src="/js/mvrvRatio.js"></script>
				<figure class="highcharts-figure">
					<div id="container" class="cotainer-fluid"></div>
					<div class="fs-4">
					
						<div class="highcharts-description"></div>
							
							<h5 id="indexinfo">MVRV Ratio</h5>
						<br/>
						
						<h5>코인의 시장가치와 실현 가치를 나누어 계산한 비율</h5>
						<br>
						<div>MVRV 비율은 시장 정서와 암호화폐의 잠재적 과대평가 또는 과소평가에 대한 통찰력을 제공</div>
						<br>
						<div>MVRV 비율 > 1: 현재 Market Value가 모든 보유자의 평균 비용 기준(Realized
							Value)보다 높다는 것을 나타냅니다. 이것은 평균적으로 투자자가 이익을 얻고 있으며 암호 화폐가 과대 평가되었을
							수 있음을 시사합니다. 그것은 잠재적인 시장 정상 또는 거품의 신호일 수 있습니다.</div>
							<br>
						<div>MVRV 비율 < 1: 현재 시장 가치가 모든 보유자의 평균 비용 기준(실현 가치)보다 낮음을
							시사합니다. 이는 평균적으로 투자자들이 손해를 보고 있으며 암호화폐가 저평가될 수 있음을 의미합니다. 투자자들에게는
							잠재적으로 유리한 가격에 매수할 수 있는 기회가 될 수 있습니다.</div>
							<br>
						<div>MVRV 비율 = 1: 시장 가치가 실현 가치와 같음을 나타냅니다. 이는 투자자가 평균적으로 이익도
							손실도 없는 공정한 평가를 제안합니다.</div>
						<br />
					</div>
				</figure>
			</div>
		</div>
	</div>

	<script src="../libs/jquery/dist/jquery.min.js"></script>
	<script src="../libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../js/sidebarmenu.js"></script>
	<script src="../js/app.min.js"></script>
	<script src="../libs/simplebar/dist/simplebar.js"></script>

	<script>
		document.getElementById("searchButton")
				.addEventListener(
						"click",
						function(event) {
							event.preventDefault();
							var searchTerm = document
									.getElementById("searchInput").value;
							var url = "/main?tvwidgetsymbol=" + searchTerm;
							window.location.href = url;
						});
	</script>
</body>
</html>
