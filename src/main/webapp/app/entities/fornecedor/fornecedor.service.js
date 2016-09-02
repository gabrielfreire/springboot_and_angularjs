(function() {
    'use strict';
    angular
        .module('xclientappApp')
        .factory('Fornecedor', Fornecedor);

    Fornecedor.$inject = ['$resource'];

    function Fornecedor ($resource) {
        var resourceUrl =  'api/fornecedors/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
