var app = angular.module('abilitiesApp', []);

app.controller('SearchAbility', [ '$scope', '$http', function($scope, $http) {
	$scope.color = [ "grey", "yellow", "violet", "teal", "brown" ];

	$scope.loadAbilities = function(filter) {
		$http.post('abilities').then(function(response) {
			$scope.abilities = response.data;
		});
	}
} ]);