(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('ProdutoController', ProdutoController);

    ProdutoController.$inject = ['$scope', '$state', 'Produto'];

    function ProdutoController ($scope, $state, Produto) {
        var vm = this;
        
        vm.produtos = [];

        loadAll();

        function loadAll() {
            Produto.query(function(result) {
                vm.produtos = result;
            });
        }
        $(document).ready(function(){
            $("#produto").css("display", "none");
            $("#produto").fadeIn(500);
        });
    }
})();
