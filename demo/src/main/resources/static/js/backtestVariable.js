$("input[name=selectedcoin]").on("click", (e) => {
	const divRatio = document.getElementById("divRatio");
	if (e.target.checked) {
		let newRatio = document.createElement('p');
		newRatio.innerHTML = "<input type='number' id='ratio' name=" + e.target.value + ">";
		divRatio.appendChild(newRatio);
	} else {
		let oldRatio = document.querySelector("input[name=" + e.target.value + "]");
		document.getElementById("divRatio").removeChild(oldRatio.parentNode);
	}
})

$("#submitbtn").click((e) => {

	const tagboxElements = document.querySelectorAll("input[name=selectedcoin]:checked");
	const coinRatioElements = document.querySelectorAll("input[id=ratio]");
	const strategyNames = document.querySelectorAll("input[name=strategy]:checked");

	var coinTickers = [];
	var coinRatios = [];
	var strategies = [];

	tagboxElements.forEach((tagboxElement) => {
		coinTickers.push(tagboxElement.value)
	})

	coinRatioElements.forEach((coinRatioElement) => {
		coinRatios.push(coinRatioElement.value)
	})

	strategyNames.forEach((strategyName) => {
		strategies.push(strategyName.value)
	}) 
	
	let ratioSum = coinRatios.reduce(function add(sum, currValue) {  return sum + Number(currValue);}, 0);
	console.log(ratioSum)


	if (tagboxElements.length >= 1 && strategies.length >= 1 && ratioSum == 1) {
		$.ajax({
			url: '/api/chartdata',
			contentType: "application/json",
			data: JSON.stringify({
				coins: coinTickers,
				ratios: coinRatios,
				strategies: strategies
			}),
			type: 'POST',
			dataType: 'json', //서버에서 리턴하는 데이터를 무엇으로 인식할지 지정 
			success: function(datas) {
				for (var data in datas) {
					var g = document.createElement("div");
					g.setAttribute("id", "chartDiv"+data);
					
					document.body.appendChild(g);
					
					datas[data].endDates = datas[data].endDates.map(val=> {
						let dt = new Date(val*1000);
						return dt.toLocaleDateString({format:"yy-mm-dd"});
					});
					var chart = Highcharts.chart('chartDiv'+data, {
						chart: {
							zoomType: 'xy'
						},
						title: {
							text: '백테스트 결과 :' +datas[data].coinNames +" + "+ datas[data].strategyName,
							align: 'left'
						},
						xAxis: [{
							categories: datas[data].endDates,
							crosshair: true,
						}],
						yAxis: [{ // Primary yAxis
							labels: {
								format: '{value}원',
								style: {
									color: Highcharts.getOptions().colors[1]
								}
							},
							title: {
								text: '종가',
								style: {
									color: Highcharts.getOptions().colors[1]
								}
							}
						}, { // Secondary yAxis
							title: {
								text: '현금흐름',
								style: {
									color: Highcharts.getOptions().colors[0]
								}
							},
							labels: {
								format: '{value}',
								style: {
									color: Highcharts.getOptions().colors[0]
								}
							},
							opposite: true
						}],
						tooltip: {
							shared: true
						},
						legend: {
							align: 'left',
							x: 80,
							verticalAlign: 'top',
							y: 60,
							floating: true,
							backgroundColor:
								Highcharts.defaultOptions.legend.backgroundColor || // theme
								'rgba(255,255,255,0.25)'
						},
						series: [{
							name: 'price',
							type: 'line',
							yAxis: 0,
							data: datas[data].combinationPrice,
							tooltip: {
								valueSuffix: ' 원'
							}

						}, {
							name: 'cashflow',
							type: 'line',
							yAxis: 1,
							data: datas[data].totalCashFlow,
							tooltip: {
								valueSuffix: ' v'
							}
						}]
					});
				}
			},
			error: function() {

			}
		});
	} else {
		const ratios = document.querySelectorAll("input[id=ratio]");
		ratios.forEach((ratio) => {
			ratio.classList.add('invalid');
		})
	}
})
