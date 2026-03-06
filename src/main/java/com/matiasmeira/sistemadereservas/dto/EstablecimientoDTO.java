package com.matiasmeira.sistemadereservas.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EstablecimientoDTO {
    
    @Data @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Entrada {
        private String nombre;
        private String direccion;
        private String telefono;
        private LocalTime horaApertura;
        private LocalTime horaCierre;
        private Long usuarioId;
    }

    @Data @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Salida {
        private Long id;
        private String nombre;
        private String direccion;
        private String telefono;
        private LocalTime horaApertura;
        private LocalTime horaCierre;
        private Long usuarioId;
    }
}
