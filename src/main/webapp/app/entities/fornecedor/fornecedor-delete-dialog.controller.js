(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('FornecedorDeleteController',FornecedorDeleteController);

    FornecedorDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fornecedor'];

    function FornecedorDeleteController($uibModalInstance, entity, Fornecedor) {
        var vm = this;

        vm.fornecedor = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Fornecedor.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
