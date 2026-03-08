package com.matiasmeira.sistemadereservas.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.matiasmeira.sistemadereservas.repository.EstablecimientoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    
    private final EstablecimientoRepository establecimientoRepository;

    public boolean esDueñoOAdmin(Long establecimientoId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) return true;

        boolean isOwner = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_OWNER"));
        if (!isOwner) return false;

        String emailLogueado = authentication.getName();
        
        return establecimientoRepository.findById(establecimientoId)
                .map(est -> est.getUsuario().getEmail().equals(emailLogueado))
                .orElse(false);
    }
}
