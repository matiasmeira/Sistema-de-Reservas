package com.matiasmeira.sistemadereservas.service;

import com.matiasmeira.sistemadereservas.dto.AuthDTO; 
import com.matiasmeira.sistemadereservas.dto.UsuarioDTO;
import com.matiasmeira.sistemadereservas.model.Usuario;
import com.matiasmeira.sistemadereservas.repository.UsuarioRepository;
import com.matiasmeira.sistemadereservas.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthDTO.Response register(UsuarioDTO.Entrada request) {
        Usuario nuevoUsuario = new Usuario();

        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setTelefono(request.getTelefono());

        nuevoUsuario.setPassword(passwordEncoder.encode(request.getPassword()));        
        if (request.getRol() == null || request.getRol().isEmpty()) {
            nuevoUsuario.setRol("ROLE_USER");
        } else {
            nuevoUsuario.setRol(request.getRol());
        }

        Usuario usuarioGuardado = repository.save(nuevoUsuario);
        String jwtToken = jwtService.generateToken(usuarioGuardado);
        
        return new AuthDTO.Response(jwtToken);
    }


    public AuthDTO.Response authenticate(AuthDTO.Request request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Usuario user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String jwtToken = jwtService.generateToken(user);
        
        return new AuthDTO.Response(jwtToken);
    }
}