(async () => {

	const client = new Apollo.lib.ApolloClient({
		networkInterface: Apollo.lib.createNetworkInterface({
			uri: 'https://api.santiment.net/graphql',
			transportBatching: true,
		}),
		connectToDevTools: true,
	})

	const QUERY = Apollo.gql`
	{
	getMetric(metric:"mvrv_usd") {
    timeseriesData(slug:"ethereum", from:"2019-01-01T00:00:00Z", to:"2023-09-01T00:00:00Z", interval:"1d") {
      datetime
      value
    }
  }
}`

	data = []

	client.query({ query: QUERY }).then(result => {
		data = result.data.getMetric.timeseriesData.map(el => {
			return [new Date(el.datetime).getTime(), el.value]
		})
		Highcharts.chart('container', {

			title: {
				text: ''
			},

			yAxis: {
				title: {
					text: 'MVRV Ratio'
				}
			},
			xAxis: {
				type: 'datetime',
				dateTimeLabelFormats: {
					day: '%Y %M %D'    //ex- 01 Jan 2016
				}
			},

			legend: {
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle'
			},

			series: [{
				name: 'MVRV',
				data: data
			}],

			responsive: {
				rules: [{
					condition: {
						maxWidth: 500
					},
					chartOptions: {
						legend: {
							layout: 'horizontal',
							align: 'center',
							verticalAlign: 'bottom'
						}
					}
				}]
			}

		});
	})
	
})();