d3.json("getRiskAllStats.web", function(data) {
    
	var convertedData = [];
	data.forEach(function(item){
		convertedData.push([item.LANE, item.LANECOUNT]);
	});
	console.log(convertedData);
    var chart = c3.generate({
	size: {
            height: 310,
            width: 450
        },
    data: {
        columns: convertedData,
        type: 'bar',
        colors: {
			Green : '#3DE20C',
            Red : '#FF3E01',
            Yellow : '#FFF148'
        },
    },
    
	bindto: '#riskStatBar',
});
});