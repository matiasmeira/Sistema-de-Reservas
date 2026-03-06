package com.matiasmeira.sistemadereservas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matiasmeira.sistemadereservas.dto.ReservaDTO;
import com.matiasmeira.sistemadereservas.service.ReservaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDTO.Salida> crear(@RequestBody ReservaDTO.Entrada reserva, @RequestParam Long canchaId, @RequestParam Long usuarioId){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.guardar(reserva, canchaId, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@RequestParam Long id){
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO.Salida>> obtenerTodas(){
        return ResponseEntity.ok(reservaService.obtenerTodas());
    }

}
