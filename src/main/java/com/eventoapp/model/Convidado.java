package com.eventoapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

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
    @NotEmpty
    @Column(nullable = false)
    private String rg;
    @NotEmpty
    @Column(name = "nome_convidado",nullable = false ,length = 150)
    private String nomeConvidado;

    @ManyToOne
    private Evento evento;
    
}

