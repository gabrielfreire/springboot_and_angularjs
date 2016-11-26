'use strict';

describe('Controller Tests', function() {

    describe('Categoria Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCategoria;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCategoria = jasmine.createSpy('MockCategoria');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Categoria': MockCategoria
            };
            createController = function() {
                $injector.get('$controller')("CategoriaDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'xclientappApp:categoriaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
        //this code is for study purposes
        describe("Testing the resource service from ANGULARJS", function(){

            it("should say that $resource is available for me", inject(function($resource){
                expect($resource).toBeDefined();
            }));
            
        });
    });

});
