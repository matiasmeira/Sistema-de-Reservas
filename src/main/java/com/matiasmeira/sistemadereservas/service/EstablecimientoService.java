package com.matiasmeira.sistemadereservas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matiasmeira.sistemadereservas.dto.EstablecimientoDTO;
import com.matiasmeira.sistemadereservas.model.Establecimiento;
import com.matiasmeira.sistemadereservas.model.Usuario;
import com.matiasmeira.sistemadereservas.repository.EstablecimientoRepository;
import com.matiasmeira.sistemadereservas.repository.UsuarioRepository;

@Service
public class EstablecimientoService {

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public EstablecimientoDTO.Salida guardar(EstablecimientoDTO.Entrada establecimiento, Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Establecimiento nuevoEstablecimiento = new Establecimiento();
        nuevoEstablecimiento.setNombre(establecimiento.getNombre());
        nuevoEstablecimiento.setDireccion(establecimiento.getDireccion());
        nuevoEstablecimiento.setTelefono(establecimiento.getTelefono());
        nuevoEstablecimiento.setHoraApertura(establecimiento.getHoraApertura());
        nuevoEstablecimiento.setHoraCierre(establecimiento.getHoraCierre());
        nuevoEstablecimiento.setUsuario(usuario);
        return MapToDTO(establecimientoRepository.save(nuevoEstablecimiento));
    }

    public EstablecimientoDTO.Salida obtenerPorId(Long id){
        return MapToDTO(establecimientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Establecimiento no encontrado")));
    }

    public void eliminar(Long id){
        if (!establecimientoRepository.existsById(id)) {
            throw new RuntimeException("Establecimiento no encontrado");
        }
        establecimientoRepository.deleteById(id);
    }

    public List<EstablecimientoDTO.Salida> obtenerTodos(){
        List<EstablecimientoDTO.Salida> establecimientosDTO = new java.util.ArrayList<>();
        List<Establecimiento> establecimientos = establecimientoRepository.findAll();
        for (Establecimiento establecimiento : establecimientos) {
            establecimientosDTO.add(MapToDTO(establecimiento));
        }
        return establecimientosDTO;
    }

    private EstablecimientoDTO.Salida MapToDTO(Establecimiento establecimiento) {
        EstablecimientoDTO.Salida salida = new EstablecimientoDTO.Salida();
        salida.setId(establecimiento.getId());
        salida.setNombre(establecimiento.getNombre());
        salida.setDireccion(establecimiento.getDireccion());
        salida.setTelefono(establecimiento.getTelefono());
        salida.setHoraApertura(establecimiento.getHoraApertura());
        salida.setHoraCierre(establecimiento.getHoraCierre());
        salida.setUsuarioId(establecimiento.getUsuario().getId());
        return salida;
    }
    
}
