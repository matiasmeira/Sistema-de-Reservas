package com.matiasmeira.sistemadereservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matiasmeira.sistemadereservas.dto.EstablecimientoDTO;
import com.matiasmeira.sistemadereservas.service.EstablecimientoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/establecimientos")
public class EstablecimientoController {
    @Autowired
    private EstablecimientoService establecimientoService;

    @PostMapping
    public ResponseEntity<EstablecimientoDTO.Salida> crear(@RequestBody EstablecimientoDTO.Entrada establecimiento, @RequestParam Long usuarioId){
        return ResponseEntity.ok(establecimientoService.guardar(establecimiento, usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablecimientoDTO.Salida> obtenerPorId(@RequestParam Long id){
        return ResponseEntity.ok(establecimientoService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<java.util.List<EstablecimientoDTO.Salida>> obtenerTodos(){
        return ResponseEntity.ok(establecimientoService.obtenerTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@RequestParam Long id){
        establecimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
