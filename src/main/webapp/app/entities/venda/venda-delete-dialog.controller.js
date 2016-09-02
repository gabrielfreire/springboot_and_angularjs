(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('VendaDeleteController',VendaDeleteController);

    VendaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Venda'];

    function VendaDeleteController($uibModalInstance, entity, Venda) {
        var vm = this;

        vm.venda = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Venda.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
