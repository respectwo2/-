<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!-- <style>
.highcharts-figure, .highcharts-data-table table {
    min-width: 360px; 
    max-width: 800px;
    margin: 1em auto;
}

.highcharts-data-table table {
	font-family: Verdana, sans-serif;
	border-collapse: collapse;
	border: 1px solid #EBEBEB;
	margin: 10px auto;
	text-align: center;
	width: 100%;
	max-width: 500px;
}
.highcharts-data-table caption {
    padding: 1em 0;
    font-size: 1.2em;
    color: #555;
}
.highcharts-data-table th {
	font-weight: 600;
    padding: 0.5em;
}
.highcharts-data-table td, .highcharts-data-table th, .highcharts-data-table caption {
    padding: 0.5em;
}
.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even) {
    background: #f8f8f8;
}
.highcharts-data-table tr:hover {
    background: #f1f7ff;
}

</style> -->

</head>

<body>

<script src="https://unpkg.com/jquery/dist/jquery.min.js"></script>
<script src="https://unpkg.com/survey-jquery@1.9.97/survey.jquery.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/survey-jquery@1.9.97/defaultV2.min.css" />
<script type="text/javascript" src="../js/mvrvRatio.js" ></script>
<script type="text/javascript" src="../js/backtest.js"></script>
<figure class="highcharts-figure">
    <div id="container"></div>
    <p class="highcharts-description">
        Example how to use Highcharts with Santiment API. We are fetching DAA and plot it.
    </p>
</figure>
<figure class="highcharts-figure">
    <div id="container2"></div>
    <p class="highcharts-description">
        Example how to use Highcharts with Santiment API. We are fetching DAA and plot it.
    </p>
</figure>

<div id="surveyElement"></div>
<script src="../js/backtestVariable.js"></script>

</body>
</html>


