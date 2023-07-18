<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BackTestResult</title>
<script src="https://code.highcharts.com/stock/highstock.js"></script>
<script src="https://code.highcharts.com/stock/modules/exporting.js"></script>
<script src="https://code.highcharts.com/stock/modules/accessibility.js"></script>
<!-- Include the library in the page -->
<script src="https://unpkg.com/apollo-client-browser@1.9.0"></script>
</head>

<body>
	<link rel="stylesheet" type="text/css" href="../css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../css/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/demo.css">
	<script type="text/javascript" src="../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>

	<!-- <script type="text/javascript" src="../js/mvrvRatio.js" ></script>
 -->
	<script type="text/javascript" src="../js/backtest.js"></script>
	
	<div class="container">
  <ul class="ks-cboxtags">
  	<c:forEach var="coin" items="${coins}">
  	    <li><input type="checkbox" name="selectedcoin" id="checkboxOne" value="${coin.coinTicker}"><label for="checkboxOne">${coin.coinTicker}</label></li>
  	</c:forEach>
  </ul>
  <div id="divRatio"></div>

</div>

	<div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="strategy"
				value="SMA"> <label class="form-check-label"
				for="inlineCheckbox1">단순이동평균선</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="inlineCheckbox2" name="strategy"
				value="EMA"> <label class="form-check-label"
				for="inlineCheckbox2">지수이동평균선</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="inlineCheckbox3" name="strategy"
				value="Bollinger1"> <label class="form-check-label"
				for="inlineCheckbox2">볼린저밴드상하한</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="inlineCheckbox4" name="strategy"
				value="Bollinger2"> <label class="form-check-label"
				for="inlineCheckbox2">볼린저밴드상한</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="inlineCheckbox5" name="strategy"
				value="RSI"> <label class="form-check-label"
				for="inlineCheckbox2">상대강도지수</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="inlineCheckbox6" name="strategy"
				value="Momentum"> <label class="form-check-label"
				for="inlineCheckbox2">추세추종</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="inlineCheckbox7" name="strategy"
				value="Supertrend"> <label class="form-check-label"
				for="inlineCheckbox2">슈퍼트렌드</label>
		</div>
	</div>

	<input id="submitbtn" type="button" value="전송" />

	<script src="../js/backtestVariable.js"></script>

	<div id="surveyElement"></div>

</body>
</html>


