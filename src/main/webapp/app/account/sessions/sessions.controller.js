(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .controller('SessionsController', SessionsController);

    SessionsController.$inject = ['Sessions', 'Principal', '$state'];

    function SessionsController (Sessions, Principal, $state) {
        var vm = this;

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
    }
})();
