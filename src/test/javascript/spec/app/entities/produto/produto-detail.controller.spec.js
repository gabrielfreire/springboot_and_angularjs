'use strict';

describe('Controller Tests', function() {

    describe('Produto Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockProduto, MockUser, MockCategoria, MockFornecedor;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockProduto = jasmine.createSpy('MockProduto');
            MockUser = jasmine.createSpy('MockUser');
            MockCategoria = jasmine.createSpy('MockCategoria');
            MockFornecedor = jasmine.createSpy('MockFornecedor');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Produto': MockProduto,
                'User': MockUser,
                'Categoria': MockCategoria,
                'Fornecedor': MockFornecedor
            };
            createController = function() {
                $injector.get('$controller')("ProdutoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'xclientappApp:produtoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
