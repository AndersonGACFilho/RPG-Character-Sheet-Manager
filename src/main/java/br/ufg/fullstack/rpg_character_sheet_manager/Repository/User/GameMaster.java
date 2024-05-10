package br.ufg.fullstack.rpg_character_sheet_manager.Repository.User;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Sessions.GameSession;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity()
public class GameMaster extends User{
    @OneToMany()
    private List<GameSession> gameSessions;
}
