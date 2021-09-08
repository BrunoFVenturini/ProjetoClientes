package com.projeto.projeto.service;

import com.projeto.projeto.model.Produto;
import com.projeto.projeto.model.Venda;
import com.projeto.projeto.repo.ProdutoRepo;
import com.projeto.projeto.repo.VendaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    private VendaRepo vendaRepo;
    private ProdutoRepo produtoRepo;

    @Autowired
    public VendaService(VendaRepo vendaRepo, ProdutoRepo produtoRepo){
        this.vendaRepo = vendaRepo;
        this.produtoRepo = produtoRepo;
    }

    public Venda adicionaVenda(Venda venda) {
        Optional<Produto> produto = produtoRepo.findById(venda.getProduto().getId());
        Long quantidadeVendido = venda.getQuantidade_vendido();

        return AlteraEstoque(produto, quantidadeVendido, venda, "Não há produtos suficientes no estoque. Venda cancelada.");
    }

    public List<Venda> retornasTodasVendas(){
        return vendaRepo.findAll();
    }

    public Venda atualizaVenda(Venda venda) {
        Optional<Produto> produto = produtoRepo.findById(venda.getProduto().getId());
        Long quantidadeAnterior = vendaRepo.findById(venda.getId()).get().getQuantidade_vendido();
        Long quantidadeNova = venda.getQuantidade_vendido();
        Long diferenca = quantidadeNova - quantidadeAnterior;

        return AlteraEstoque(produto, diferenca, venda, "Não há produtos suficientes no estoque. Alteração cancelada.");
    }

    public void deletaVenda (Long id){
        Optional<Venda> venda = vendaRepo.findById(id);
        Optional<Produto> produto = produtoRepo.findById(venda.get().getProduto().getId());

        reiterarEstoque(produto, venda.get().getQuantidade_vendido());


        vendaRepo.deleteById(id);
    }
    public Optional<Venda> buscaVendaPorId(Long id){
        return vendaRepo.findById(id);
    }

    public void reiterarEstoque(Optional<Produto> produto, Long quantidade_reiterada){
        if(produto.isPresent()){
            produto.get().setQuantidade_estoque((int) (produto.get().getQuantidade_estoque() + quantidade_reiterada));
        }
    }

    public boolean verificaEstoque(Optional<Produto> produto, Long compra){
        return produto.get().getQuantidade_estoque() - compra < 0;
    }

    public Venda AlteraEstoque(Optional<Produto> produto, Long valor, Venda venda, String mensagem){
        if(!verificaEstoque(produto, valor) && produto.isPresent()) {

            produto.get().setQuantidade_estoque((int) (produto.get().getQuantidade_estoque() -  valor));

            return vendaRepo.save(venda);
        }
        throw new EntityNotFoundException(mensagem);
    }




}
