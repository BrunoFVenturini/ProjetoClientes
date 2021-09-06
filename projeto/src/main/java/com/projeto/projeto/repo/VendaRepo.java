package com.projeto.projeto.repo;

import com.projeto.projeto.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepo extends JpaRepository<Venda, Long> {
}
