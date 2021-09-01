angular.module("projetoVenda").service("vendasAPI", function($http, config){

    this.getVendas = function(){
        return $http.get(config.baseUrl + "/venda/all")
    }

    this.saveVenda = function(venda){
        return $http.post(config.baseUrl + "/venda/add", venda)
    }

    this.deleteVenda = function(id){
        return $http.delete(config.baseUrl + "/venda/delete/" + id)
    }
});