(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('VendaController', VendaController);

    VendaController.$inject = ['$scope', '$state', 'Venda'];

    function VendaController ($scope, $state, Venda) {
        var vm = this;
        
        vm.vendas = [];

        loadAll();

        function loadAll() {
            Venda.query(function(result) {
                vm.vendas = result;
            });
        }

        $(document).ready(function(){
            $("#venda").css("display", "none");
            $("#venda").fadeIn(500);
        });
    }
})();
