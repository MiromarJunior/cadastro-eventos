package com.eventoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventoapp.model.Convidado;
import com.eventoapp.model.Evento;

public interface ConvidadoRepository extends JpaRepository<Convidado ,String> {
    List<Convidado> findByEvento(Evento evento );
    Convidado findByRg(String rg);
    
}
