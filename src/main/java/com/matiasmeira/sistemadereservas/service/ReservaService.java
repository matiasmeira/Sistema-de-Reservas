package com.matiasmeira.sistemadereservas.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matiasmeira.sistemadereservas.dto.ReservaDTO;
import com.matiasmeira.sistemadereservas.model.Cancha;
import com.matiasmeira.sistemadereservas.model.Reserva;
import com.matiasmeira.sistemadereservas.model.Usuario;
import com.matiasmeira.sistemadereservas.repository.CanchaRepository;
import com.matiasmeira.sistemadereservas.repository.ReservaRepository;
import com.matiasmeira.sistemadereservas.repository.UsuarioRepository;

@Service
public class ReservaService {
    @Autowired
    private CanchaRepository canchaRepository;

    @Autowired
    private ReservaRepository reservaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ReservaDTO.Salida guardar(ReservaDTO.Entrada reserva, String emailUsuario) {
        
        Cancha cancha = canchaRepository.findById(reserva.getCanchaId()).orElseThrow(() -> new RuntimeException("Cancha no encontrada"));
                
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));   
        
        LocalTime horaFinCalculada = reserva.getHoraInicio().plusMinutes(reserva.getDuracionMinutos());

        boolean ocupada = reservaRepository.existeSolapamiento(
                cancha.getId(),
                reserva.getFechaReserva(),
                reserva.getHoraInicio(),
                horaFinCalculada
        );

        if (ocupada) {
            throw new RuntimeException("La cancha ya se encuentra reservada en ese horario.");
        }

        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setFechaReserva(reserva.getFechaReserva());
        nuevaReserva.setHoraInicio(reserva.getHoraInicio());
        nuevaReserva.setDuracionMinutos(reserva.getDuracionMinutos()); 
        nuevaReserva.setHoraFin(horaFinCalculada);
        
        nuevaReserva.setPrecioTotal((reserva.getDuracionMinutos() * cancha.getPrecioHora()) / 60.0);
        
        nuevaReserva.setEstado("Reservada");
        nuevaReserva.setCancha(cancha);
        nuevaReserva.setUsuario(usuario);
        
        return MapToDTO(reservaRepository.saveAndFlush(nuevaReserva));
    }

    public ReservaDTO.Salida obtenerPorId(Long id){
        return MapToDTO(reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada")));
    }

    public void eliminar(Long id){
        if (!reservaRepository.existsById(id)) {
            throw new RuntimeException("Reserva no encontrada");
        }
        reservaRepository.deleteById(id);
    }

    public List<ReservaDTO.Salida> obtenerMisReservas(String emailUsuario) {
        
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Reserva> reservas;

        if (usuario.getRol().equals("ROLE_ADMIN")) {
            reservas = reservaRepository.findAll();
            
        } else if (usuario.getRol().equals("ROLE_OWNER")) {
            reservas = reservaRepository.findByEstablecimientoDueñoEmail(emailUsuario);
            
        } else { 
            reservas = reservaRepository.findByUsuarioEmail(emailUsuario);
        }

        List<ReservaDTO.Salida> reservasDTO = new ArrayList<>();
        for (Reserva reserva : reservas) {
            reservasDTO.add(MapToDTO(reserva));
        }
        
        return reservasDTO;
    }

    private ReservaDTO.Salida MapToDTO(Reserva reserva) {
        ReservaDTO.Salida salida = new ReservaDTO.Salida();
        salida.setId(reserva.getId());
        salida.setFechaReserva(reserva.getFechaReserva());
        salida.setHoraInicio(reserva.getHoraInicio());
        salida.setHoraFin(reserva.getHoraFin());
        salida.setDuracionMinutos(reserva.getDuracionMinutos());
        salida.setPrecioTotal(reserva.getPrecioTotal());
        salida.setEstado(reserva.getEstado());
        salida.setFechaCreacion(reserva.getFechaCreacion());
        return salida;
    }
}
