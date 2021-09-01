package com.projeto.projeto.repo;

import com.projeto.projeto.model.Cliente;
import com.projeto.projeto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllClienteByname(String name);
}
