package com.matiasmeira.sistemadereservas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matiasmeira.sistemadereservas.dto.CanchaDTO;
import com.matiasmeira.sistemadereservas.model.Cancha;
import com.matiasmeira.sistemadereservas.model.Establecimiento;
import com.matiasmeira.sistemadereservas.repository.CanchaRepository;
import com.matiasmeira.sistemadereservas.repository.EstablecimientoRepository;

@Service
public class CanchaService {
    @Autowired
    private CanchaRepository canchaRepository;

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    public CanchaDTO.Salida guardar(CanchaDTO.Entrada cancha, Long establecimientoId){
        Establecimiento establecimiento = establecimientoRepository.findById(establecimientoId).orElseThrow(() -> new RuntimeException("Establecimiento no encontrado"));
        Cancha nuevaCancha = new Cancha();
        nuevaCancha.setNombre(cancha.getNombre());
        nuevaCancha.setUbicacion(cancha.getUbicacion());
        nuevaCancha.setTipo(cancha.getTipo());
        nuevaCancha.setPrecioHora(cancha.getPrecioHora());
        nuevaCancha.setEstado("Disponible");
        nuevaCancha.setDescripcion(cancha.getDescripcion());
        nuevaCancha.setEstablecimiento(establecimiento);
        return MapToDTO(canchaRepository.save(nuevaCancha));
    }

    public CanchaDTO.Salida obtenerPorId(Long id){
        return MapToDTO(canchaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cancha no encontrada")));
    }

    public List<CanchaDTO.Salida> obtenerTodas(){
        List<CanchaDTO.Salida> canchasDTO = new ArrayList<>();
        List<Cancha> canchas = canchaRepository.findAll();
        for (Cancha cancha : canchas) {
            canchasDTO.add(MapToDTO(cancha));
        }
        return canchasDTO;
    }
    
    public void eliminar(Long id){
        if (!canchaRepository.existsById(id)) {
            throw new RuntimeException("Cancha no encontrada");
        }
        canchaRepository.deleteById(id);
    }

    private CanchaDTO.Salida MapToDTO(Cancha cancha) {
        CanchaDTO.Salida salida = new CanchaDTO.Salida();
        salida.setId(cancha.getId());
        salida.setNombre(cancha.getNombre());
        salida.setTipo(cancha.getTipo());
        salida.setPrecioHora(cancha.getPrecioHora());
        salida.setEstado(cancha.getEstado());
        salida.setUbicacion(cancha.getUbicacion());
        salida.setDescripcion(cancha.getDescripcion());
        salida.setEstablecimientoId(cancha.getEstablecimiento().getId());
        return salida;
    }
}