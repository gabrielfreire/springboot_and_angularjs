(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('FornecedorDialogController', FornecedorDialogController);

    FornecedorDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fornecedor', 'Produto', 'User'];

    function FornecedorDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fornecedor, Produto, User) {
        var vm = this;

        vm.fornecedor = entity;
        vm.clear = clear;
        vm.save = save;
        vm.produtos = Produto.query();
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.fornecedor.id !== null) {
                Fornecedor.update(vm.fornecedor, onSaveSuccess, onSaveError);
            } else {
                Fornecedor.save(vm.fornecedor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclientappApp:fornecedorUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
