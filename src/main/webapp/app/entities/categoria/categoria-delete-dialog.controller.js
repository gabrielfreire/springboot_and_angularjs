(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('CategoriaDeleteController',CategoriaDeleteController);

    CategoriaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Categoria'];

    function CategoriaDeleteController($uibModalInstance, entity, Categoria) {
        var vm = this;

        vm.categoria = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Categoria.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
