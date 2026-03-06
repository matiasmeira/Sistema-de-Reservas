package com.matiasmeira.sistemadereservas.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CanchaDTO {

    @Data @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Entrada {
        private String nombre;
        private String tipo;
        private double precioHora;
        private String ubicacion;
        private String descripcion;
        private Long usuarioId;
    }

    @Data @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Salida {
        private Long id;
        private String nombre;
        private String tipo;
        private double precioHora;
        private String estado;
        private String ubicacion;
        private String descripcion;
        private String nombreUsuario;
        private List <ReservaDTO.Salida> reservas;
    }
    
}
