package com.vaxi.spring_boot_microservice_2_compra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vaxi.spring_boot_microservice_2_compra.model.Compra;


public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findAllByUserId(Long userId);
}
