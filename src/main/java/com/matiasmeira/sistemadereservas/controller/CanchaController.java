package com.matiasmeira.sistemadereservas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matiasmeira.sistemadereservas.service.CanchaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.matiasmeira.sistemadereservas.dto.CanchaDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/canchas")
public class CanchaController {
    @Autowired
    private CanchaService canchaService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    public ResponseEntity<CanchaDTO.Salida> crear(@RequestBody CanchaDTO.Entrada cancha, @RequestParam Long usuarioId ){
        return ResponseEntity.status(HttpStatus.CREATED).body(canchaService.guardar(cancha, usuarioId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@securityUtils.esDueñoOAdminCancha(#id, authentication)")
    public ResponseEntity<CanchaDTO.Salida> actualizar(@PathVariable Long id, @RequestBody CanchaDTO.Entrada cancha) {
        return ResponseEntity.ok(canchaService.actualizar(id, cancha));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@securityUtils.esDueñoDeLaCancha(#id, authentication)")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        canchaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<CanchaDTO.Salida>> obtenerTodas(){
        return ResponseEntity.ok(canchaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<CanchaDTO.Salida> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(canchaService.obtenerPorId(id));
    }
}
