
var app = angular.module('permitapp', ['ngRoute']);

app.config(["$routeProvider", "$locationProvider", function($routeProvider, $locationProvider){
    $routeProvider
		.when("/riskall", {
			templateUrl: "riskall.html"
			
		})
		.when("/riskpermit", {
			templateUrl: "riskPermitPage.web",
			
		})
		// .otherwise({ redirectTo: '/'})
		;
}]);

app.controller('jobHeaderRecentCtrl',function($scope, $http){
        $scope.currentPage = 0;
        $scope.pageSize = 5;
    	$http({method: 'GET', url: 'processedRecentJob.web'}).
        success(function(data, status, headers, config) {
        	$scope.recentJob = data;
        }).
        error(function(data, status, headers, config) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
});


app.controller('riskallstatsctrl',function($scope, $http){
    	$http({method: 'GET', url: 'getRiskAllStats.web'}).
        success(function(data, status, headers, config) {
        	$scope.riskAllStatsData = data;
			$scope.greenLaneStat = data[0].LANECOUNT;
			$scope.redLaneStat = data[1].LANECOUNT;
			$scope.yellowLaneStat = data[2].LANECOUNT;
			$scope.totalLaneStat = $scope.greenLaneStat + $scope.redLaneStat + $scope.yellowLaneStat;
        }).
        error(function(data, status, headers, config) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
});

app.controller('riskpermitstatsctrl',function($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getRiskPermitStats.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.riskPermitStatsData = data;
        $scope.riskScoreStat = data.RISK_SCORE;
        $scope.paScoreStat = data.PA_SCORE;
        $scope.adScoreStat = data.AD_SCORE;
        $scope.rbStat = data.RB_SCORE;
    });
    response.error(function (data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
});

app.controller('riskpermitdetailsctrl',function($scope, $http, srvShareData){
	   var searchParam = {
	  				      	userId : srvShareData.getData()[0].userId,
	  				        jobNo : srvShareData.getData()[0].jobNo
	  				     };
        var response = $http.post('getRiskPermitDetails.web', searchParam);
    	response.success(function(data, status, headers, config) {
					        	$scope.permitData = data;
        					});
        response.error(function(data, status, headers, config) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
});

app.controller('riskpermitdetailsIEctrl',function($scope, $http, srvShareData){
	   var searchParam = {
	  				      	userId : srvShareData.getData()[0].userId,
	  				        jobNo : srvShareData.getData()[0].jobNo
	  				     };
        var response = $http.post('getRiskPermitIEDetails.web', searchParam);
    	response.success(function(data, status, headers, config) {
					        	$scope.ieData = data;
        					});
        response.error(function(data, status, headers, config) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
        $scope.getAllParty = function(){
            var response = $http.post('getPermitAllPartyList.web', searchParam);
            response.success(function(data, status, headers, config) {
					        	$scope.allPartyData = data;
        					});
        response.error(function(data, status, headers, config) {
        });
        };
});

app.controller('permitItemsListctrl',function($scope, $http, srvShareData){
	   var searchParam = {
	  				      	userId : srvShareData.getData()[0].userId,
	  				        jobNo : srvShareData.getData()[0].jobNo
	  				     };
        var response = $http.post('getPermitItemsList.web', searchParam);
    	response.success(function(data, status, headers, config) {
					        	$scope.itemsListData = data;
        					});
        response.error(function(data, status, headers, config) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
});

app.controller('permitallpartyctrl',function($scope, $http, srvShareData){
	   var searchParam = {
	  				      	userId : srvShareData.getData()[0].userId,
	  				        jobNo : srvShareData.getData()[0].jobNo
	  				     };
         var response = $http.post('getPermitAllPartyList.web', searchParam);
            response.success(function(data, status, headers, config) {
					        	$scope.allPartyData = data;
        					});
        response.error(function(data, status, headers, config) {
        });
});

app.controller('permitcontainerctrl',function($scope, $http, srvShareData){
	   var searchParam = {
	  				      	userId : srvShareData.getData()[0].userId,
	  				        jobNo : srvShareData.getData()[0].jobNo
	  				     };
         var response = $http.post('getPermitContainersList.web', searchParam);
            response.success(function(data, status, headers, config) {
					        	$scope.containerListData = data;
        					});
        response.error(function(data, status, headers, config) {
        });
});

app.controller('permitinvoicectrl',function($scope, $http, srvShareData){
	   var searchParam = {
	  				      	userId : srvShareData.getData()[0].userId,
	  				        jobNo : srvShareData.getData()[0].jobNo
	  				     };
         var response = $http.post('getPermitInvoicesList.web', searchParam);
            response.success(function(data, status, headers, config) {
					        	$scope.invoiceListData = data;
        					});
        response.error(function(data, status, headers, config) {
        });
});

app.controller('permitsupdocctrl',function($scope, $http, srvShareData){
	   var searchParam = {
	  				      	userId : srvShareData.getData()[0].userId,
	  				        jobNo : srvShareData.getData()[0].jobNo
	  				     };
         var response = $http.post('getPermitSupDocList.web', searchParam);
            response.success(function(data, status, headers, config) {
					        	$scope.supdocListData = data;
        					});
        response.error(function(data, status, headers, config) {
        });
});


app.controller('riskListCtrl',function($scope, $http, srvShareData, $location){
    	$http({method: 'GET', url: 'getRiskList.web'}).
        success(function(data, status, headers, config) {
        	$scope.riskListData = data;
        }).
        error(function(data, status, headers, config) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
        $scope.setLinkParam = function(riskData) {
	  			$scope.riskDataToShare = {
	  				                       userId : riskData.USER_ID,
	  				                       jobNo : riskData.JOB_NO
	  				                      };
	    		srvShareData.addData($scope.riskDataToShare);
	    		window.location.href = "riskPermitPage.web";
			};
});

app.service('srvShareData', function($window) {
        var RISK_PERMIT_KEY = 'Risk.Permit.SelectedValue';

        var addData = function(newObj) {
            /*var mydata = $window.sessionStorage.getItem(KEY);
            if (mydata) {
                mydata = JSON.parse(mydata);
            } else {
                mydata = [];
            }*/
            var mydata = [];
            mydata.push(newObj);
            $window.sessionStorage.setItem(RISK_PERMIT_KEY, JSON.stringify(mydata));
        };

        var getData = function(){
            var mydata = $window.sessionStorage.getItem(RISK_PERMIT_KEY);
            if (mydata) {
                mydata = JSON.parse(mydata);
            }
            return mydata || [];
        };

        return {
            addData: addData,
            getData: getData
        };
    });

app.service('sharedRiskProperties', function () {
        var jobNo = '';
		var userId = '';
        return {
            getJobNo: function () {
                return jobNo;
            },
            setJobNo: function(value) {
                jobNo = value;
            },
            getUserId: function () {
                return userId;
            },
            setUserId: function(value) {
                userId = value;
            }
        };
    });


app.controller('riskpermitchartstatsctrl',function($scope, $http, srvShareData){
    var searchParam = {
        userId : srvShareData.getData()[0].userId,
        jobNo : srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getRiskPermitStats.web', searchParam);
    response.success(function(data, status, headers, config) {
        $scope.riskPermitStatsData = data;
        $scope.riskScoreStat = data.RISK_SCORE;
        $scope.paScoreStat = data.PA_SCORE;
        $scope.adScoreStat = data.AD_SCORE;
        $scope.rbStat = data.RB_SCORE;

        var convertedData = [];
        convertedData.push(['RISK SCORE', data.RISK_SCORE]);
        convertedData.push(['PREDICTIVE ANALYSIS SCORE', data.PA_SCORE]);
        convertedData.push(['ANAMOLY DETECTION SCORE', data.AD_SCORE]);
        convertedData.push(['RULE BASE SCORE', data.RB_SCORE]);

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
    response.error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
});


app.controller('getPermitListCtrl',function($scope, $http){
        $scope.currentPage = 0;
        $scope.pageSize = 5;
    	$http({method: 'GET', url: 'getPermitList.web'}).
        success(function(data, status, headers, config) {
        	$scope.permitList = data;
        }).
        error(function(data, status, headers, config) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
});



