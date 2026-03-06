package com.matiasmeira.sistemadereservas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matiasmeira.sistemadereservas.service.CanchaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.matiasmeira.sistemadereservas.dto.CanchaDTO;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/canchas")
public class CanchaController {
    @Autowired
    private CanchaService canchaService;

    @PostMapping
    public ResponseEntity<CanchaDTO.Salida> crear(@RequestBody CanchaDTO.Entrada cancha, @RequestParam Long usuarioId ){
        return ResponseEntity.status(HttpStatus.CREATED).body(canchaService.guardar(cancha, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@RequestParam Long id){
        canchaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CanchaDTO.Salida>> obtenerTodas(){
        return ResponseEntity.ok(canchaService.obtenerTodas());
    }
}
