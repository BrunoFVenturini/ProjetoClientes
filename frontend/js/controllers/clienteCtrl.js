angular.module("projetoVenda").controller("clienteCtrl", function($scope, clientesAPI, vendasAPI){

    var carregarClientes = function(){
        clientesAPI.getClientes().then(function(response){
            $scope.clientes = response.data;
        }).catch(function(response){
            $scope.message = "Erro " + response.data.details; 
        });
    }

    $scope.adicionaCliente = function(cliente){
        cliente.horario_cadastro = new Date();
        clientesAPI.saveCliente(cliente).then(function(response){
            delete $scope.cliente;
            carregarClientes();
        }).catch(function(response){
            $scope.message = "Erro " + response.data.details;
        })
    }

    $scope.deletaCliente = function(clientes){
        let clientesFiltrados = clientes.filter(function(cliente){
            if(cliente.selecionado) return cliente
        })       

        if(!verificaVendas(clientesFiltrados))
        {
            for (let i = 0; i < clientesFiltrados.length; i++) {
                clientesAPI.deleteCliente(clientesFiltrados[i].id).then(function(response){
                    carregarClientes();
                })
            }
        }
    }

    var verificaVendas = function(clientes){
        let vendas;
        vendasAPI.getVendas().then(function(response){
            vendas = response.data;
            
            for (let i = 0; i < clientes.length; i++) {
                
                for (let y = 0; y < vendas.length; y++) {

                    console.log(clientes[i].id + '   ' + vendas[y].cliente.id)

                    if(vendas[y].cliente.id == clientes[i].id){
                        
                        $scope.message = "Você não pode deletar um cliente que está vinculado à uma venda!"
                        return true;
                    }
                    
                }            
            }
        }); 
    }

    $scope.isClienteSelecionado = function(clientes) {
        return clientes.some(function (cliente){
            return cliente.selecionado;
        });
    };

    carregarClientes();

})