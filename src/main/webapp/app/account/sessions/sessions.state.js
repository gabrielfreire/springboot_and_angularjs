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
            url: '/estoque',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'global.menu.account.sessions'
            },
            views: {
                'content@': {
                    templateUrl: 'app/account/sessions/estoque/estoque.html',
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
        .state('dashboard.clientes', {
            parent: 'dashboard',
            url: '/clientes',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'global.menu.account.sessions'
            },
            views: {
                'content@': {
                    templateUrl: 'app/account/sessions/clientes/clientes.html',
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
        .state('dashboard.vendas', {
            url: '/vendas',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'global.menu.account.sessions'
            },
            views: {
                'content@': {
                    templateUrl: 'app/account/sessions/vendas/vendas.html',
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
        });
    }
})();
