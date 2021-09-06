angular.module("projetoVenda").config(function($routeProvider){
    $routeProvider.when("/produtos", {
        templateUrl: "/view/produtos.html",
        controller: "produtoCtrl"
    });
    $routeProvider.when("/clientes", {
        templateUrl: "/view/clientes.html",
        controller: "clienteCtrl"
    });
    $routeProvider.when("/vendas",{
        templateUrl: "view/vendas.html",
        controller: "vendaCtrl"
    })
})