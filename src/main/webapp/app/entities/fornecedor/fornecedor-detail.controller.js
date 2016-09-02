(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('FornecedorDetailController', FornecedorDetailController);

    FornecedorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Fornecedor', 'Produto', 'User'];

    function FornecedorDetailController($scope, $rootScope, $stateParams, entity, Fornecedor, Produto, User) {
        var vm = this;

        vm.fornecedor = entity;

        var unsubscribe = $rootScope.$on('xclientappApp:fornecedorUpdate', function(event, result) {
            vm.fornecedor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
