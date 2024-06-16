package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Sessions;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.Stores.Store;
import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Sheets.CharacterSheet;
import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Sheets.NPCSheet;
import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Skills.SkillType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany()
    private List<SkillType> skillType;

    @OneToMany()
    private List<CharacterSheet> players;
    @OneToMany()
    private List<NPCSheet> npcs;
    @OneToMany()
    private List<SessionLog> logs;

    @OneToMany()
    private List<Store> stores;
}
