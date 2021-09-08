package com.projeto.projeto;

import com.projeto.projeto.model.Venda;
import com.projeto.projeto.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venda")
public class VendaResource {

    @Autowired
    private final VendaService vendaService;

    public VendaResource(VendaService vendaService){
        this.vendaService = vendaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Venda>> buscaVendas(){
        List<Venda> vendas = vendaService.retornasTodasVendas();
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Venda>> buscaVendaPorId(@PathVariable("id")Long id){
        Optional<Venda> venda = vendaService.buscaVendaPorId(id);
        if(venda.isPresent()){
            return new ResponseEntity<>(venda, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Venda> adicionaVenda(@RequestBody Venda venda){
        Venda newVenda = vendaService.adicionaVenda(venda);
        return new ResponseEntity<>(newVenda, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity atualizaVenda(@RequestBody Venda venda) {
        Venda updatedVenda = vendaService.atualizaVenda(venda);

        return new ResponseEntity<>(updatedVenda, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletaVenda(@PathVariable("id") Long id){
        vendaService.deletaVenda(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
