package com.projeto.projeto;

import com.projeto.projeto.model.Cliente;
import com.projeto.projeto.model.Produto;
import com.projeto.projeto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {

    @Autowired
    private final ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService){
        this.produtoService = produtoService;
    }


    @GetMapping("/")
    public ResponseEntity<List<Produto>> buscaProdutos(){
        List<Produto> produtos = produtoService.retornaTodosProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Produto>> buscaProdutoPorId(@PathVariable("id")Long id){
        Optional<Produto> produto = produtoService.buscaProdutoPorId(id);
        if(produto.isPresent()){
            return new ResponseEntity<>(produto,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Produto> adicionaProduto(@RequestBody Produto produto){
        Produto newProduto = produtoService.adicionaProduto(produto);
        return new ResponseEntity<>(newProduto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Produto> atualizaProduto (@RequestBody Produto produto){
        Produto updatedProduto = produtoService.atualizaProduto(produto);
        return new ResponseEntity<>(updatedProduto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletaProduto(@PathVariable("id") Long id){
        produtoService.deletaProduto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletaListaProdutos(@RequestParam List<Long> idProdutos){
        produtoService.deletaListaProduto(idProdutos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Produto>> buscaProdutoPorNome(@RequestParam String name){
        List<Produto> produto = produtoService.buscaProdutoPorNome(name);
        return new ResponseEntity<>(produto,HttpStatus.OK);

    }
}
