package com.projeto.projeto.service;

import com.projeto.projeto.model.Cliente;
import com.projeto.projeto.model.Venda;
import com.projeto.projeto.repo.ClienteRepo;
import com.projeto.projeto.repo.VendaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepo clienteRepo;
    private final VendaRepo vendaRepo;

    @Autowired
    public ClienteService(ClienteRepo clienteRepo, VendaRepo vendaRepo) {
        this.clienteRepo = clienteRepo;
        this.vendaRepo = vendaRepo;
    }

    public Cliente adicionaCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public List<Cliente> retornaTodosClientes() {
        return clienteRepo.findAll();
    }

    public Cliente atualizaCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public void deletaCliente(Long id){
        if (!existeVinculoVenda(id)){
            clienteRepo.deleteById(id);
        }
    }

    public void deletaListaCliente(List<Long> idClientes){
        String clientesVinculados = "";

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        if(!clienteRepo.findAllById(idClientes).isEmpty()) {
            clientes = (ArrayList<Cliente>) clienteRepo.findAllById(idClientes);

            for (int i = 0; i < clientes.toArray().length; i++) {
                if (!existeVinculoVenda(clientes.get(i).getId())) {
                    clienteRepo.deleteById(clientes.get(i).getId());
                } else {
                    clientesVinculados += clientes.get(i).getName() + " / ";
                }
            }

            if (clientesVinculados != "") {
                throw new EntityNotFoundException("Os seguintes clientes não foram deletados pois estão vínculados à uma venda: "
                        + clientesVinculados);
            }
        }
    }

    public Optional<Cliente> buscaClientePorId(Long id){
        return clienteRepo.findById(id);
    }

    public List<Cliente> buscaClientePorNome(String name){
        return clienteRepo.findAllClienteByname(name);
    }

    public Boolean existeVinculoVenda(Long id){
        List<Venda> vendas = vendaRepo.findAll();


        for (int y = 0; y < vendas.toArray().length; y++) {

            if (id == vendas.get(y).getCliente().getId()) {

                return true;
            }
        }
        return false;

    }


}
