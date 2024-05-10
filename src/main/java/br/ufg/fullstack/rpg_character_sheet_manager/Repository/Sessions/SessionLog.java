package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Sessions;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class SessionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date updateDate;
    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String log;

}
