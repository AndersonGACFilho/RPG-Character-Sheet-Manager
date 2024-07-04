package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import java.io.Serializable;

import org.springframework.context.annotation.ImportRuntimeHints;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Conversion implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the conversion
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//Não entendi bem como funciona essa classe


}
