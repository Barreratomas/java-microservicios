package com.vaxi.spring_boot_microservice_2_compra.service;

import java.util.List;

import com.vaxi.spring_boot_microservice_2_compra.model.Compra;

public interface CompraService {

    Compra saveCompra(Compra compra);

    List<Compra> findByUserId(Long userId);

}
