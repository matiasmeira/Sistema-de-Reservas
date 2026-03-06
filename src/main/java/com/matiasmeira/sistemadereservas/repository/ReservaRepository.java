package com.matiasmeira.sistemadereservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matiasmeira.sistemadereservas.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> { 
} 
