package br.ufg.fullstack.rpg_character_sheet_manager.repositories;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRuleRepository extends JpaRepository<GameRule, Long> {

    /**
     * Find all game rules by game session ID
     * @param gameSessionId ID of the game session
     * @param pageable PageRequest object
     * @return Page<GameRule> with all game rules
     */
    Page<GameRule> findByGameSessionId(Long gameSessionId, Pageable pageable);
}