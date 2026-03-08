package com.matiasmeira.sistemadereservas.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReservaDTO {

    @Data @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Entrada {
        private long canchaId; 
        private LocalDate fechaReserva;
        private LocalTime horaInicio;
        private int duracionMinutos;
    }

    @Data @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Salida {
        private Long id;
        private LocalDate fechaReserva;
        private LocalTime horaInicio;
        private LocalTime horaFin;
        private int duracionMinutos;
        private String estado;
        private double precioTotal;
        private LocalDateTime fechaCreacion;
        private String nombreUsuario;
        private String nombreCancha;
    }
}
