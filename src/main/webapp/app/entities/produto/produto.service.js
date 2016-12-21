(function() {
    'use strict';
    angular
        .module('xclientappApp')
        .factory('Produto', Produto);

    Produto.$inject = ['$resource', 'DateUtils'];

    function Produto ($resource, DateUtils) {
        var resourceUrl =  'api/produtos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataValidade = DateUtils.convertLocalDateFromServer(data.dataValidade);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.dataValidade = DateUtils.convertLocalDateToServer(data.dataValidade);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.dataValidade = DateUtils.convertLocalDateToServer(data.dataValidade);
                    return angular.toJson(data);
                }
            }
        });
    }
})();
