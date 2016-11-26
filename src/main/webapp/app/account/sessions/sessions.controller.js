(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('SessionsController', SessionsController);

    SessionsController.$inject = ['$http', 'Sessions', 'Principal', '$state', 'Produto', 'Venda', 'Cliente'];

    function SessionsController ($http, Sessions, Principal, $state, Produto, Venda, Cliente) {
        var vm = this;

        vm.produtos = Produto.query();
        vm.vendas = Venda.query();
        vm.clientes = Cliente.query();
        vm.$state = $state;
        vm.tab = 1;
        vm.setTab = function(value){
            return vm.tab = value;
        }
        vm.isSet = function(value){
            if(vm.tab == value){
                return true;
            }else{
                return false;
            }
        }
        vm.account = null;
        vm.error = null;
        vm.invalidate = invalidate;
        //vm.sessions = Sessions.getAll();
        vm.success = null;


        Principal.identity().then(function(account) {
            vm.account = account;
        });
       
        function invalidate (series) {
            Sessions.delete({series: encodeURIComponent(series)},
                function () {
                    vm.error = null;
                    vm.success = 'OK';
                    vm.sessions = Sessions.getAll();
                },
                function () {
                    vm.success = null;
                    vm.error = 'ERROR';
                });
        }
        
        $(document).ready(function(){
            $("#panel").css("display", "none");
            $("#panel").fadeIn(500);
        }); 
       
        
        
    }
})();
