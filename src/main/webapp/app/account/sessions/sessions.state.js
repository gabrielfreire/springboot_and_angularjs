(function() {
    'use strict';

    angular
        .module('xclientappApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('dashboard', {
            parent: 'account',
            url: '/dashboard',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'global.menu.account.sessions'
            },
            views: {
                'dashboard@': {
                    templateUrl: 'app/account/sessions/sessions.html',
                    controller: 'SessionsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('sessions');
                    return $translate.refresh();
                }]
            }
        }).state('dashboard.painel', {
            parent: 'dashboard',
            url: '/painel',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'global.menu.account.sessions'
            },
            views: {
                'content@': {
                    templateUrl: 'app/account/sessions/painel/painel.html',
                    controller: 'SessionsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('sessions');
                    return $translate.refresh();
                }]
            }
        })
        .state('dashboard.estoque', {
            parent: 'dashboard',
            url: '/estoque',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'xclientappApp.produto.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/produto/produtos.html',
                    controller: 'ProdutoController',
                    controllerAs: 'vm'
                },
                'categoria@':{
                    templateUrl: 'app/entities/categoria/categorias.html',
                    controller: 'CategoriaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('sessions');
                    $translatePartialLoader.addPart('produto');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('dashboard.detalheProduto', {
            parent: 'dashboard.estoque',
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
        .state('dashboard.novoProduto', {
            parent: 'dashboard.estoque',
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
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('dashboard.estoque', null, { reload: true });
                }, function() {
                    $state.go('dashboard.estoque');
                });
            }]
        })
        .state('dashboard.editarProduto', {
            parent: 'dashboard.estoque',
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
                    $state.go('dashboard.estoque', null, { reload: true });
                }, function() {
                    $state.go('dashboard.estoque');
                });
            }]
        })
        .state('dashboard.deletarProduto', {
            parent: 'dashboard.estoque',
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
                    $state.go('dashboard.estoque', null, { reload: true });
                }, function() {
                    $state.go('dashboard.estoque');
                });
            }]
        })
        .state('dashboard.clientes', {
            parent: 'dashboard',
            url: '/clientes',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'xclientappApp.cliente.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cliente/clientes.html',
                    controller: 'ClienteController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('sessions');
                    $translatePartialLoader.addPart('cliente');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('dashboard.detalheCliente', {
            parent: 'dashboard.clientes',
            url: '/cliente/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'xclientappApp.cliente.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cliente/cliente-detail.html',
                    controller: 'ClienteDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('cliente');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Cliente', function($stateParams, Cliente) {
                    return Cliente.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('dashboard.novoCliente', {
            parent: 'dashboard.clientes',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cliente/cliente-dialog.html',
                    controller: 'ClienteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                clienteNomeCompleto: null,
                                clienteEmail: null,
                                clienteTelefone: null,
                                clienteCelular: null,
                                clienteCpfCnpj: null,
                                clienteRua: null,
                                clienteCodPostal: null,
                                clienteCidade: null,
                                clienteEstado: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('dashboard.clientes', null, { reload: true });
                }, function() {
                    $state.go('dashboard.clientes');
                });
            }]
        })
        .state('dashboard.editarCliente', {
            parent: 'dashboard.clientes',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cliente/cliente-dialog.html',
                    controller: 'ClienteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Cliente', function(Cliente) {
                            return Cliente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dashboard.clientes', null, { reload: true });
                }, function() {
                    $state.go('dashboard.clientes');
                });
            }]
        })
        .state('dashboard.deletarCliente', {
            parent: 'dashboard.clientes',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cliente/cliente-delete-dialog.html',
                    controller: 'ClienteDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Cliente', function(Cliente) {
                            return Cliente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dashboard.clientes', null, { reload: true });
                }, function() {
                    $state.go('dashboard.clientes');
                });
            }]
        })
        .state('dashboard.vendas', {
            parent: 'dashboard',
            url: '/vendas',
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
                    $translatePartialLoader.addPart('sessions');
                    $translatePartialLoader.addPart('venda');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('dashboard.detalheVenda', {
            parent: 'dashboard.vendas',
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
        .state('dashboard.novoVenda', {
            parent: 'dashboard.vendas',
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
                    $state.go('dashboard.vendas', null, { reload: true });
                }, function() {
                    $state.go('dashboard.vendas');
                });
            }]
        })
        .state('dashboard.editarVenda', {
            parent: 'dashboard.vendas',
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
                    $state.go('dashboard.vendas', null, { reload: true });
                }, function() {
                    $state.go('dashboard.vendas');
                });
            }]
        })
        .state('dashboard.deletarVenda', {
            parent: 'dashboard.vendas',
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
                    $state.go('dashboard.vendas', null, { reload: true });
                }, function() {
                    $state.go('dashboard.vendas');
                });
            }]
        });
    }
})();
