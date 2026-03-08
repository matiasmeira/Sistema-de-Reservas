package com.matiasmeira.sistemadereservas.controller;

import com.matiasmeira.sistemadereservas.dto.AuthDTO;
import com.matiasmeira.sistemadereservas.dto.UsuarioDTO;
import com.matiasmeira.sistemadereservas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthDTO.Response> register(@RequestBody UsuarioDTO.Entrada request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO.Response> authenticate(@RequestBody AuthDTO.Request request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}