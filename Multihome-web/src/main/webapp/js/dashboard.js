/**
 * 
 */

var app = angular.module('myApp', []);
app.controller('MyController',function($scope, $http){
	$scope.getPersonDataFromServer = function() {	    	
    	$http({method: 'GET', url: 'test.web'}).
        success(function(data, status, headers, config) {
        	$scope.person = data;
        }).
        error(function(data, status, headers, config) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
    
    };
});

