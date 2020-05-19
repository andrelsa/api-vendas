package com.dev.andrelsa.domain.repository;

import com.dev.andrelsa.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidos extends JpaRepository<ItemPedido, Integer> {

}
