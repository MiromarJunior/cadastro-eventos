package com.eventoapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "convidado")
public class Convidado {

    @Id
    private String rg;

    @Column(name = "nome_convidado", length = 150)
    private String nomeConvidado;

    @ManyToOne
    private Evento evento;
    
}

