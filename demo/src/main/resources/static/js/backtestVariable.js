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


	if (tagboxElements.length > 1 && strategies.length > 1) {
		fetch("http://localhost:8080/api/chartdata", {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify({
					coins: coinTickers,
					ratios: coinRatios,
					strategies: strategies
				}),
			}).then(response => {
				console.log(response.json());
			}).then(result => {
				console.log(result);
			});
			
			/*console.log(backtestChartdatas); // a 가, b 나, c 다

			for (var backtestChartdata in backtestChartdatas) {
				console.log(backtestChartdata); // a 가, b 나, c 다
			}*/
		

	}
})
