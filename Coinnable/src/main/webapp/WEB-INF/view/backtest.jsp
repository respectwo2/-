<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="layout/header.jsp"%>
<script src="https://code.highcharts.com/stock/highstock.js"></script>
<script src="https://code.highcharts.com/stock/modules/exporting.js"></script>
<script src="https://code.highcharts.com/stock/modules/accessibility.js"></script>
<!-- Include the library in the page -->
<script src="https://unpkg.com/apollo-client-browser@1.9.0"></script>
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/checkboxTag.css">

<div class="container-fluid">
	<div class="card">
		<div class="card-body">
			<div class="fs-6 text-dark">백테스트 적용할 코인을 고르세요</div>
			<ul class="ks-cboxtags">
				<c:forEach var="coin" items="${coins}">
					<li><input type="checkbox" name="selectedcoin"
						id="checkbox${coin.coinId}" value="${coin.coinTicker}"><label
						for="checkbox${coin.coinId }">${coin.coinTicker}</label></li>
				</c:forEach>
			</ul>
			<br>
			<div class="fs-6 text-dark">각 코인의 비율을 적어주세요</div>
			<div id="divRatio" class="gap-2"></div>
		</div>
	</div>

	<div class="card">
		<div class="card-body">
			<div class="fs-6 text-dark">백테스트 적용할 전략을 고르세요</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" id="inlineCheckbox1"
					name="strategy" value="SMA"> <label
					class="form-check-label" for="inlineCheckbox1">단순이동평균선</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" id="inlineCheckbox2"
					name="strategy" value="EMA"> <label
					class="form-check-label" for="inlineCheckbox2">지수이동평균선</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" id="inlineCheckbox3"
					name="strategy" value="Bollinger1"> <label
					class="form-check-label" for="inlineCheckbox3">볼린저밴드상하한</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" id="inlineCheckbox4"
					name="strategy" value="Bollinger2"> <label
					class="form-check-label" for="inlineCheckbox4">볼린저밴드상한</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" id="inlineCheckbox5"
					name="strategy" value="RSI"> <label
					class="form-check-label" for="inlineCheckbox5">상대강도지수</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" id="inlineCheckbox6"
					name="strategy" value="Momentum"> <label
					class="form-check-label" for="inlineCheckbox6">추세추종</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" id="inlineCheckbox7"
					name="strategy" value="Supertrend"> <label
					class="form-check-label" for="inlineCheckbox7">슈퍼트렌드</label>
			</div>
		</div>
		<input class="btn btn-primary fs-6" id="submitbtn" type="button" value="백테스트 실행" />
		
	</div>
	
<div class="card">
	<div class="card-body">
	<div id="divChart"></div>
	</div>
	</div>
	<script src="../js/backtestVariable.js"></script>

</div>