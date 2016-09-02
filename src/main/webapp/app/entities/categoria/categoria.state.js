(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('categoria', {
            parent: 'entity',
            url: '/categoria',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'xclientappApp.categoria.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/categoria/categorias.html',
                    controller: 'CategoriaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('categoria');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('categoria-detail', {
            parent: 'entity',
            url: '/categoria/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'xclientappApp.categoria.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/categoria/categoria-detail.html',
                    controller: 'CategoriaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('categoria');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Categoria', function($stateParams, Categoria) {
                    return Categoria.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('categoria.new', {
            parent: 'categoria',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoria/categoria-dialog.html',
                    controller: 'CategoriaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                catNome: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('categoria', null, { reload: true });
                }, function() {
                    $state.go('categoria');
                });
            }]
        })
        .state('categoria.edit', {
            parent: 'categoria',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoria/categoria-dialog.html',
                    controller: 'CategoriaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Categoria', function(Categoria) {
                            return Categoria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('categoria', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('categoria.delete', {
            parent: 'categoria',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoria/categoria-delete-dialog.html',
                    controller: 'CategoriaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Categoria', function(Categoria) {
                            return Categoria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('categoria', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
