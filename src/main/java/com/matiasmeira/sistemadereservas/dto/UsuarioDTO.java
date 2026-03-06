package com.matiasmeira.sistemadereservas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UsuarioDTO {
    @Data @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Entrada{
        private String email;
        private String contraseña;
        private String rol;
        private String nombre;
        private String telefono;
    }

    @Data @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Salida {
        private Long id;
        private String email;
        private String contraseña;
        private String rol;
        private String nombre;
        private String telefono;
    }
    
}
