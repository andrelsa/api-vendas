package com.dev.andrelsa.domain.repository;

import com.dev.andrelsa.domain.entity.ItemPedido;
import com.dev.andrelsa.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidos extends JpaRepository<ItemPedido, Integer> {

}
