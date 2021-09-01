package com.projeto.projeto.repo;

import com.projeto.projeto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepo extends JpaRepository<Produto, Long> {
    List<Produto> findAllProdutoByname(String name);
}
