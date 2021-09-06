angular.module("projetoVenda").service("produtosAPI", function($http, config){
    this.getProdutos = function(){
        return $http.get(config.baseUrl + "/produto/all")
    }
    this.saveProduto = function(produto){
        return $http.post(config.baseUrl + "/produto/add", produto)
    }
    this.getProdutoById = function(id){
        return $http.get(config.baseUrl + "/produto/find/" + id);
    }
    this.deleteProduto = function(id){
        return $http.delete(config.baseUrl + "/produto/delete/" + id)
    }
})