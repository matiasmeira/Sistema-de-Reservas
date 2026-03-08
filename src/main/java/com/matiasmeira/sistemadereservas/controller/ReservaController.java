package com.matiasmeira.sistemadereservas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matiasmeira.sistemadereservas.dto.ReservaDTO;
import com.matiasmeira.sistemadereservas.service.ReservaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'OWNER', 'ADMIN')")
    public ResponseEntity<ReservaDTO.Salida> crear(@RequestBody ReservaDTO.Entrada reserva, Authentication authentication){
        String emailUsuario = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.guardar(reserva, emailUsuario));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@securityUtils.tieneAccesoALaReserva(#id, authentication)")    public ResponseEntity<Void> eliminar(@RequestParam Long id){
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("@securityUtils.tieneAccesoALaReserva(#id, authentication)")    public ResponseEntity<ReservaDTO.Salida> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.obtenerPorId(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    public ResponseEntity<List<ReservaDTO.Salida>> obtenerMisReservas(Authentication authentication){
        String emailUsuario = authentication.getName();
        return ResponseEntity.ok(reservaService.obtenerMisReservas(emailUsuario));
    }
}
