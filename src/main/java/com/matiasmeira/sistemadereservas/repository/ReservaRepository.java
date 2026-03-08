package com.matiasmeira.sistemadereservas.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matiasmeira.sistemadereservas.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> { 
        @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reserva r " +
            "WHERE r.cancha.id = :canchaId " +
            "AND r.fechaReserva = :fechaReserva " +
            "AND r.estado != 'CANCELADA' " +
            "AND (r.horaInicio < :horaFin AND r.horaFin > :horaInicio)")
        boolean existeSolapamiento(
                @Param("canchaId") Long canchaId,
                @Param("fechaReserva") LocalDate fechaReserva,
                @Param("horaInicio") LocalTime horaInicio,
                @Param("horaFin") LocalTime horaFin
        );

        List<Reserva> findByUsuarioEmail(String email);

        @Query("SELECT r FROM Reserva r WHERE r.cancha.establecimiento.usuario.email = :email")
        List<Reserva> findByEstablecimientoDueñoEmail(@Param("email") String email);

} 

