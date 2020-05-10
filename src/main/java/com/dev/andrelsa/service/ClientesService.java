package com.dev.andrelsa.service;

import com.dev.andrelsa.model.Cliente;
import com.dev.andrelsa.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public void salvarCliente(Cliente cliente) {
        this.clientesRepository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente) {
        //aplica validações;
    }
}
