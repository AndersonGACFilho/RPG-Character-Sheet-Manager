package br.ufg.fullstack.rpg_character_sheet_manager.repositories;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRuleRepository extends JpaRepository<GameRule, Long> {
}