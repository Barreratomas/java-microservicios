package com.vaxi.spring_boot_microservice_1_inmueble.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaxi.spring_boot_microservice_1_inmueble.model.Inmueble;

public interface InmuebleRepository extends JpaRepository<Inmueble, Long> {




}
