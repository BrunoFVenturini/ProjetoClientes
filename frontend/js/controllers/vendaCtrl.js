angular.module("projetoVenda").controller("vendaCtrl", function($scope, vendasAPI, produtosAPI, clientesAPI){
    
    var carregarProdutos = function(){
        produtosAPI.getProdutos().then(function(response){
            $scope.produtos = response.data;
        }).catch(function(response){
            $scope.message = "Erro " + response.data; 
        });
    }

    var carregarClientes = function(){
        clientesAPI.getClientes().then(function(response){
            $scope.clientes = response.data;
        }).catch(function(response){
            $scope.message = "Erro " + response.data.details; 
        });
    }

    var carregarVendas = function(){
        vendasAPI.getVendas().then(function(response){
            $scope.vendas = response.data;
        }).catch(function(response){
            $scope.message = "Erro " + response.data.details;
        });
    }

    $scope.adicionaVenda = function(venda){       
        venda.produto = {id: venda.produto};
        venda.cliente = {id: venda.cliente};
        venda.horario_venda = new Date();
        
        vendasAPI.saveVenda(venda).then(function(response){
            
            delete $scope.venda;
            carregarVendas();
            carregarProdutos();
        }).catch(function(response){
            
            $scope.message = "Erro: " + response.data.details;
            console.log(response);
        })
    }

    $scope.isVendaSelecionado = function(vendas) {
        return vendas.some(function (venda){
            return venda.selecionado;
        });
    };

    $scope.deletaVenda = function(vendas){
        let vendasFiltradas = vendas.filter(function(venda){
            if(venda.selecionado) return venda
        })       

        for (let i = 0; i < vendasFiltradas.length; i++) {
            vendasAPI.deleteVenda(vendasFiltradas[i].id).then(function(response){
                carregarVendas();
                carregarProdutos();
            }).catch
        } 
    }

    carregarClientes();
    carregarProdutos();
    carregarVendas();
});