package com.matiasmeira.sistemadereservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matiasmeira.sistemadereservas.model.Cancha;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, Long> {
}
