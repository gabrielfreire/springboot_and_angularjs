(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('VendaDialogController', VendaDialogController);

    VendaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Venda', 'Cliente', 'Produto'];

    function VendaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Venda, Cliente, Produto) {
        var vm = this;

        vm.venda = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.clientes = Cliente.query();
        vm.produtos = Produto.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.venda.id !== null) {
                Venda.update(vm.venda, onSaveSuccess, onSaveError);
            } else {
                Venda.save(vm.venda, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclientappApp:vendaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.pedidoData = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
