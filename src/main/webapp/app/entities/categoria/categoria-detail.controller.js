(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('CategoriaDetailController', CategoriaDetailController);

    CategoriaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Categoria'];

    function CategoriaDetailController($scope, $rootScope, $stateParams, entity, Categoria) {
        var vm = this;

        vm.categoria = entity;

        var unsubscribe = $rootScope.$on('xclientappApp:categoriaUpdate', function(event, result) {
            vm.categoria = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
