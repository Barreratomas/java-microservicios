package com.vaxi.spring_boot_microservice_1_inmueble.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vaxi.spring_boot_microservice_1_inmueble.model.Inmueble;
import com.vaxi.spring_boot_microservice_1_inmueble.repository.InmuebleRepository;

@Service 
public class InmuebleServiceImpl implements InmuebleService {

     
    private final InmuebleRepository inmuebleRepository;

    public InmuebleServiceImpl(InmuebleRepository inmuebleRepository) {
        this.inmuebleRepository = inmuebleRepository;
    }



    @Override
    public Inmueble saveInmueble(Inmueble inmueble) {
        inmueble.setLocalDateTime_fechaCreacion(java.time.LocalDateTime.now().toString());
        
        return inmuebleRepository.save(inmueble);
    }

    @Override
    public void deleteInmueble(Long id) {
        inmuebleRepository.deleteById(id);
    }

    @Override
    public List<Inmueble> getAllInmuebles() {
        return inmuebleRepository.findAll();
    }

}
