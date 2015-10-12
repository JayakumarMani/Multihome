var riskallapp = angular.module('riskallapp', []);

riskallapp.controller('riskallstatsctrl',function($scope, $http){
    	$http({method: 'GET', url: 'getRiskAllStats.web'}).
        success(function(data, status, headers, config) {
        	$scope.riskAllStatsData = data;
        }).
        error(function(data, status, headers, config) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
});