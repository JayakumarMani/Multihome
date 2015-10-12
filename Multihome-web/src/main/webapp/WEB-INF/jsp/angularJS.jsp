<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring MVC AngularJS demo</title>
<script src="js/angular.min.js"></script>
<script src="js/dashboard.js"></script>

</head>
<body>
	<div data-ng-app="myApp">
		<div data-ng-controller="MyController">
			<button data-ng-click="getPersonDataFromServer()">Get Person data from server</button>
			<table>
			<tr ng-repeat="x in person">
			<td>{{x.rotation_number}}</td>
			</tr>
			</table>
			
		</div>
	</div>
	
</body>
</html>