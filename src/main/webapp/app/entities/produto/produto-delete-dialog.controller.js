(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('ProdutoDeleteController',ProdutoDeleteController);

    ProdutoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Produto'];

    function ProdutoDeleteController($uibModalInstance, entity, Produto) {
        var vm = this;

        vm.produto = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Produto.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
