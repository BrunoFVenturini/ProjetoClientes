package com.projeto.projeto.service;

import com.projeto.projeto.model.Produto;
import com.projeto.projeto.model.Venda;
import com.projeto.projeto.repo.ProdutoRepo;
import com.projeto.projeto.repo.VendaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepo produtoRepo;
    private final VendaRepo vendaRepo;


    @Autowired
    public ProdutoService(ProdutoRepo produtoRepo, VendaRepo vendaRepo){
        this.produtoRepo = produtoRepo;
        this.vendaRepo = vendaRepo;
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

    public void deletaListaProduto(List<Long> idProdutos) {
        String produtosVinculados = "";

        ArrayList<Produto> produtos = new ArrayList<>();

        if(!produtoRepo.findAllById(idProdutos).isEmpty()) {
            produtos = (ArrayList<Produto>) produtoRepo.findAllById(idProdutos);

            for (int i = 0; i < produtos.toArray().length; i++) {
                if (!existeVinculoVenda(produtos.get(i).getId())) {
                    produtoRepo.deleteById(produtos.get(i).getId());
                } else {
                    produtosVinculados += produtos.get(i).getName() + " / ";
                }
            }

            if (produtosVinculados != "") {
                throw new EntityNotFoundException("Os seguintes produtos não foram deletados pois estão vínculados à uma venda: "
                        + produtosVinculados);
            }
        }
    }

    private boolean existeVinculoVenda(Long id) {
        List<Venda> vendas = vendaRepo.findAll();

        for (int y = 0; y < vendas.toArray().length; y++) {
            if(id == vendas.get(y).getProduto().getId()){
                return true;
            }
        }
        return false;
    }
}
