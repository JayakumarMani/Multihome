d3.json("getRiskPermitStats.web").header("Content-Type", "application/json").post(function(data) {
    
	var convertedData = [];
	
		convertedData.push(['RISK SCORE', data.RISK_SCORE]);
		convertedData.push(['PREDICTIVE ANALYSIS SCORE', data.PA_SCORE]);
		convertedData.push(['ANAMOLY DETECTION SCORE', data.AD_SCORE]);
		convertedData.push(['RULE BASE SCORE', data.RB_SCORE]);
	
	console.log(convertedData);
    var chart = c3.generate({
	size: {
            height: 310,
            width: 450
        },
    data: {
        columns: convertedData,
        type: 'pie',
        /*colors: {
			Green : '#3DE20C',
            Red : '#FF3E01',
            Yellow : '#FFF148'
        },*/
    },
    
	bindto: '#riskPermitStatPie',
});
});