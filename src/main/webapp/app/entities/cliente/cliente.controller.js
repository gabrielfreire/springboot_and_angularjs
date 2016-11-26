(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('ClienteController', ClienteController);

    ClienteController.$inject = ['$scope', '$state', 'Cliente'];

    function ClienteController ($scope, $state, Cliente) {
        var vm = this;
        
        vm.clientes = [];

        loadAll();

        function loadAll() {
            Cliente.query(function(result) {
                vm.clientes = result;
            });
        }

        $(document).ready(function(){
            $("#clientes").css("display", "none");
            $("#clientes").fadeIn(500);
        });
    }
})();
