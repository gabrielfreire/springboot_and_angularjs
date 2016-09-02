(function() {
    'use strict';
    angular
        .module('xclientappApp')
        .factory('Venda', Venda);

    Venda.$inject = ['$resource', 'DateUtils'];

    function Venda ($resource, DateUtils) {
        var resourceUrl =  'api/vendas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.pedidoData = DateUtils.convertDateTimeFromServer(data.pedidoData);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
