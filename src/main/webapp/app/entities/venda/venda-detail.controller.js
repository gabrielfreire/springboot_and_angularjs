(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('VendaDetailController', VendaDetailController);

    VendaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Venda', 'Cliente', 'Produto'];

    function VendaDetailController($scope, $rootScope, $stateParams, entity, Venda, Cliente, Produto) {
        var vm = this;

        vm.venda = entity;

        var unsubscribe = $rootScope.$on('xclientappApp:vendaUpdate', function(event, result) {
            vm.venda = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
