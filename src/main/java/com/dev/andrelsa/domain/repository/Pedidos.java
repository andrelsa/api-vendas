package com.dev.andrelsa.domain.repository;

import com.dev.andrelsa.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
