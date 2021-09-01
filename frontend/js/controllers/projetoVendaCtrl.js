angular.module("projetoVenda").controller("projetoVendaCtrl", function($scope, produtosAPI){
    $scope.app = "Projeto Venda";
    $scope.produtos = [];
    $scope.clientes = [];
    $scope.vendas = [];

    $scope.ordenarPor = function(campo) {
        $scope.criterioDeOrdenacao = campo;
        $scope.inverso = !$scope.inverso
    };
})