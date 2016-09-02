(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('ClienteDetailController', ClienteDetailController);

    ClienteDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Cliente', 'Venda', 'User'];

    function ClienteDetailController($scope, $rootScope, $stateParams, entity, Cliente, Venda, User) {
        var vm = this;

        vm.cliente = entity;

        var unsubscribe = $rootScope.$on('xclientappApp:clienteUpdate', function(event, result) {
            vm.cliente = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
