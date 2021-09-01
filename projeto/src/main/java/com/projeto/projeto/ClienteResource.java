package com.projeto.projeto;

import com.projeto.projeto.model.Cliente;
import com.projeto.projeto.model.Produto;
import com.projeto.projeto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

    @Autowired
    private final ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> buscaClientes (){
        List<Cliente> clientes = clienteService.retornaTodosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Cliente>> buscaClientesPorId (@PathVariable("id") Long id){
        Optional<Cliente> cliente = clienteService.buscaClientePorId(id);
        if (cliente.isPresent()){
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<Cliente> adicionaCliente(@RequestBody Cliente cliente){
        Cliente newCliente = clienteService.adicionaCliente(cliente);
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Cliente> atualizaCliente(@RequestBody Cliente cliente){
        Cliente updatedCliente = clienteService.atualizaCliente(cliente);
        return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> deletaCliente(@PathVariable("id") Long id){
        clienteService.deletaCliente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Cliente>> buscaClientePorNome(@RequestParam String name){
        List<Cliente> cliente = clienteService.buscaClientePorNome(name);
        return new ResponseEntity<>(cliente,HttpStatus.OK);

    }
}
