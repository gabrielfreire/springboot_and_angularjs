'use strict';

describe('Controller Tests', function() {

    describe('Venda Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockVenda, MockCliente, MockProduto;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockVenda = jasmine.createSpy('MockVenda');
            MockCliente = jasmine.createSpy('MockCliente');
            MockProduto = jasmine.createSpy('MockProduto');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Venda': MockVenda,
                'Cliente': MockCliente,
                'Produto': MockProduto
            };
            createController = function() {
                $injector.get('$controller')("VendaDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'xclientappApp:vendaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
