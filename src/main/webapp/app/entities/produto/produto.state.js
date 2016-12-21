(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('produto', {
            parent: 'entity',
            url: '/produto',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'xclientappApp.produto.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/produto/produtos.html',
                    controller: 'ProdutoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('produto');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('produto-detail', {
            parent: 'entity',
            url: '/produto/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'xclientappApp.produto.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/produto/produto-detail.html',
                    controller: 'ProdutoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('produto');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Produto', function($stateParams, Produto) {
                    return Produto.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('produto.new', {
            parent: 'produto',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/produto/produto-dialog.html',
                    controller: 'ProdutoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                prodCodigo: null,
                                prodNome: null,
                                prodMarca: null,
                                prodPreco: null,
                                prodQtd: null,
                                prodCor: null,
                                valorCompra: null,
                                qtdMinima: null,
                                dataValidade: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('produto', null, { reload: true });
                }, function() {
                    $state.go('produto');
                });
            }]
        })
        .state('produto.edit', {
            parent: 'produto',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/produto/produto-dialog.html',
                    controller: 'ProdutoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Produto', function(Produto) {
                            return Produto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('produto', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('produto.delete', {
            parent: 'produto',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/produto/produto-delete-dialog.html',
                    controller: 'ProdutoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Produto', function(Produto) {
                            return Produto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('produto', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
