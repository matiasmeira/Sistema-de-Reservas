package com.matiasmeira.sistemadereservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matiasmeira.sistemadereservas.dto.EstablecimientoDTO;
import com.matiasmeira.sistemadereservas.service.EstablecimientoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/establecimientos")
public class EstablecimientoController {
    @Autowired
    private EstablecimientoService establecimientoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    public ResponseEntity<EstablecimientoDTO.Salida> crear(@RequestBody EstablecimientoDTO.Entrada establecimiento, @RequestParam Long usuarioId){
        return ResponseEntity.ok(establecimientoService.guardar(establecimiento, usuarioId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<EstablecimientoDTO.Salida> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(establecimientoService.obtenerPorId(id));
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<java.util.List<EstablecimientoDTO.Salida>> obtenerTodos(){
        return ResponseEntity.ok(establecimientoService.obtenerTodos());
    }

    @PutMapping("/{id}")
    @PreAuthorize("@securityUtils.esDueñoOAdminEstablecimiento(#id, authentication)")
    public ResponseEntity<EstablecimientoDTO.Salida> actualizar(
            @PathVariable Long id, 
            @RequestBody EstablecimientoDTO.Entrada establecimiento) {
        return ResponseEntity.ok(establecimientoService.actualizar(id, establecimiento));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@securityUtils.esDueñoOAdminEstablecimiento(#id, authentication)")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        establecimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
