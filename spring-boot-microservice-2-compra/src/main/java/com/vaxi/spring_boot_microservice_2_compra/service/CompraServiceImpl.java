package com.vaxi.spring_boot_microservice_2_compra.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vaxi.spring_boot_microservice_2_compra.model.Compra;
import com.vaxi.spring_boot_microservice_2_compra.repository.CompraRepository;

@Service 
public class CompraServiceImpl implements CompraService{
    private CompraRepository compraRepository;

    public CompraServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }



    @Override 
    public Compra saveCompra(Compra compra){
        compra.setFechaCompra(LocalDateTime.now());

        return compraRepository.save(compra);
    }

    @Override
    public List<Compra> findByUserId(Long userId){
        return compraRepository.findAllByUserId(userId);
    }
}
