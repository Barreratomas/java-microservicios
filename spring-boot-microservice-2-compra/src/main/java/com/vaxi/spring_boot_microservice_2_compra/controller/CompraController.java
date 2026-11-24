package com.vaxi.spring_boot_microservice_2_compra.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaxi.spring_boot_microservice_2_compra.model.Compra;
import com.vaxi.spring_boot_microservice_2_compra.service.CompraService;

@RestController 
@RequestMapping ("api/compra")
public class CompraController {

    private CompraService compraService;
    
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }


    @PostMapping 
    public ResponseEntity<Compra> saveCompra(@RequestBody Compra compra){
        Compra savedCompra = compraService.saveCompra(compra);
        return new ResponseEntity<>(savedCompra, HttpStatus.CREATED);

    }

    @GetMapping("{userId}")
    public ResponseEntity<List<Compra>> getAllComprasOfUser(@PathVariable Long userId){
        
        List<Compra> compras = compraService.findByUserId(userId);
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }
}
