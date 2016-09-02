(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('venda', {
            parent: 'entity',
            url: '/venda',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'xclientappApp.venda.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/venda/vendas.html',
                    controller: 'VendaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('venda');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('venda-detail', {
            parent: 'entity',
            url: '/venda/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'xclientappApp.venda.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/venda/venda-detail.html',
                    controller: 'VendaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('venda');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Venda', function($stateParams, Venda) {
                    return Venda.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('venda.new', {
            parent: 'venda',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/venda/venda-dialog.html',
                    controller: 'VendaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                pedidoData: null,
                                pedidoQtd: null,
                                formaDePagamento: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('venda', null, { reload: true });
                }, function() {
                    $state.go('venda');
                });
            }]
        })
        .state('venda.edit', {
            parent: 'venda',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/venda/venda-dialog.html',
                    controller: 'VendaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Venda', function(Venda) {
                            return Venda.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('venda', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('venda.delete', {
            parent: 'venda',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/venda/venda-delete-dialog.html',
                    controller: 'VendaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Venda', function(Venda) {
                            return Venda.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('venda', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
