$(function() {
	$.ajax({
		type : "post",
		url : appUrl + "/monitorAction!searchHessian.jspa",
		success : function(chartEntityList) {
			var xAxis = chartEntityList[0].att;
			renderChart(xAxis, chartEntityList);
		}
	});

});

function renderChart(xAxis, series) {

	$('#hessianHostory').highcharts({
		chart : {
			type : 'line',
			marginRight : 200,
			marginBottom : 30
		},
		title : {
			text : 'Hessian接口历史记录',
			x : -20
		},
		subtitle : {
			text : 'kintiger.technology Co., LTD'
		},

		xAxis : {
			categories : xAxis
		},
		yAxis : {
			title : {
				text : '日调用次数'
			},
			plotLines : [ {
				value : 0,
				width : 1
			} ]
		},
		tooltip : {
			valueSuffix : '次'
		},
		legend : {
			layout : 'vertical',
			align : 'right',
			verticalAlign : 'top',
			x : -10,
			y : 100,
			borderWidth : 1
		},
		series : series
	});

}