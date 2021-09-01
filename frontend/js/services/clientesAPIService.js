angular.module("projetoVenda").service("clientesAPI", function($http, config){
    this.getClientes = function(){
        return $http.get(config.baseUrl + "/cliente/all");
    }
    this.saveCliente = function(cliente){
        return $http.post(config.baseUrl + "/cliente/add", cliente);
    }
    this.getClienteById = function(id){
        return $http.get(config.baseUrl + "/cliente/find/" + id);
    }
    this.deleteCliente = function(id){
        return $http.delete(config.baseUrl + "/cliente/delete/" + id)
        
    }
})