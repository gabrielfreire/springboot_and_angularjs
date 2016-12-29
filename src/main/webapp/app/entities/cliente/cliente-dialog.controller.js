(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('ClienteDialogController', ClienteDialogController);

    ClienteDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Cliente', 'Venda', 'User'];

    function ClienteDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Cliente, Venda, User) {
        var vm = this;

        vm.cliente = entity;
        vm.clear = clear;
        vm.save = save;
        vm.vendas = Venda.query();
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });
        

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.cliente.id !== null) {
                Cliente.update(vm.cliente, onSaveSuccess, onSaveError);
            } else {
                Cliente.save(vm.cliente, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclientappApp:clienteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
