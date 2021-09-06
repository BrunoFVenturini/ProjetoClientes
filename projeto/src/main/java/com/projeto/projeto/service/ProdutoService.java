package com.projeto.projeto.service;

import com.projeto.projeto.model.Produto;
import com.projeto.projeto.repo.ProdutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private ProdutoRepo produtoRepo;

    @Autowired
    public ProdutoService(ProdutoRepo produtoRepo){
        this.produtoRepo = produtoRepo;
    }

    public Produto adicionaProduto(Produto produto){
        return produtoRepo.save(produto);
    }

    public List<Produto> retornaTodosProdutos(){
        return produtoRepo.findAll();
    }

    public Produto atualizaProduto(Produto produto){
        return produtoRepo.save(produto);
    }

    public void deletaProduto(Long id){
        produtoRepo.deleteById(id);
    }

    public Optional<Produto> buscaProdutoPorId(Long id){
        return produtoRepo.findById(id);
    }

    public List<Produto> buscaProdutoPorNome(String name){
        return produtoRepo.findAllProdutoByname(name);
    }
}
