package com.matiasmeira.sistemadereservas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuthDTO {

    @Data @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Request{
        private String email;
        private String password;
    }

    @Data @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Response{
        private String token;
    }

}