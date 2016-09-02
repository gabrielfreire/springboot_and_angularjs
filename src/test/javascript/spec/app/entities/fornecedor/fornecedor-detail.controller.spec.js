'use strict';

describe('Controller Tests', function() {

    describe('Fornecedor Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockFornecedor, MockProduto, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockFornecedor = jasmine.createSpy('MockFornecedor');
            MockProduto = jasmine.createSpy('MockProduto');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Fornecedor': MockFornecedor,
                'Produto': MockProduto,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("FornecedorDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'xclientappApp:fornecedorUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
