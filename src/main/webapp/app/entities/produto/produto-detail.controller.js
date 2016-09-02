(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('ProdutoDetailController', ProdutoDetailController);

    ProdutoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Produto', 'User', 'Categoria', 'Fornecedor'];

    function ProdutoDetailController($scope, $rootScope, $stateParams, entity, Produto, User, Categoria, Fornecedor) {
        var vm = this;

        vm.produto = entity;

        var unsubscribe = $rootScope.$on('xclientappApp:produtoUpdate', function(event, result) {
            vm.produto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
