
var app = angular.module('jobheaderapp', []);
app.controller('jobHeaderRecentCtrl',function($scope, $http){
        $scope.currentPage = 0;
        $scope.pageSize = 5;
    	$http({method: 'GET', url: 'processedRecentJob.web'}).
        success(function(data, status, headers, config) {
        	$scope.recentJob = data;
                    TableManaged.init();
        }).
        error(function(data, status, headers, config) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
});

