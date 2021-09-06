angular.module("projetoVenda").controller("produtoCtrl", function($scope, produtosAPI, vendasAPI){
 
    var carregarProdutos = function(){
        produtosAPI.getProdutos().then(function(response){
            $scope.produtos = response.data;
        }).catch(function(response){
            $scope.message = "Erro " + response.data.details; 
        });
    }

    $scope.adicionaProduto = function(produto){
        produtosAPI.saveProduto(produto).then(function(response){
            delete $scope.produto;
            carregarProdutos();
        }).catch(function(response){
            $scope.message = "Erro " + response.data.details;
        });
    }

    $scope.deletaProduto = function(produtos){
        let produtosFiltrados = produtos.filter(function(produto){
            if(produto.selecionado) return produto
        })       

        if(!verificaVendas(produtosFiltrados)){
            for (let i = 0; i < produtosFiltrados.length; i++) {
            
                produtosAPI.deleteProduto(produtosFiltrados[i].id).then(function(response){
                    carregarProdutos();
                }) 
            }
        }
    }

    var verificaVendas = function(produtos){
        let vendas;
        vendasAPI.getVendas().then(function(response){
            vendas = response.data;
            
            for (let i = 0; i < produtos.length; i++) {
                
                for (let y = 0; y < vendas.length; y++) {

                    console.log(produtos[i].id + '   ' + vendas[y].produto.id)

                    if(vendas[y].produto.id == produtos[i].id){
                        
                        $scope.message = "Você não pode deletar um produto que está vinculado à uma venda!"
                        return true;
                    }
                    
                }            
            }
        }); 
    }

    $scope.isProdutoSelecionado = function(produtos) {
        return produtos.some(function (produto){
            return produto.selecionado;
        });
    };

    carregarProdutos();
})