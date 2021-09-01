package com.projeto.projeto.service;

import com.projeto.projeto.model.Cliente;
import com.projeto.projeto.model.Produto;
import com.projeto.projeto.repo.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepo clienteRepo;

    @Autowired
    public ClienteService(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
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
        clienteRepo.deleteById(id);
    }

    public Optional<Cliente> buscaClientePorId(Long id){
        return clienteRepo.findById(id);
    }

    public List<Cliente> buscaClientePorNome(String name){
        return clienteRepo.findAllClienteByname(name);
    }


}
