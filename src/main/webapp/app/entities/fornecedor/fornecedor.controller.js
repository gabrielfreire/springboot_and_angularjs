(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('FornecedorController', FornecedorController);

    FornecedorController.$inject = ['$scope', '$state', 'Fornecedor'];

    function FornecedorController ($scope, $state, Fornecedor) {
        var vm = this;
        
        vm.fornecedors = [];

        loadAll();

        function loadAll() {
            Fornecedor.query(function(result) {
                vm.fornecedors = result;
            });
        }
    }
})();
