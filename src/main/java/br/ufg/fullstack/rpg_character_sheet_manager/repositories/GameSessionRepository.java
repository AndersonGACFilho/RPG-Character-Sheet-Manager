package br.ufg.fullstack.rpg_character_sheet_manager.repositories;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, Long> {

    /**
     * Finds a game sessions by master.
     * @param masterId the master ID
     */
    List<GameSession> findByMasterId(Long masterId);

    /**
     * Finds a game sessions by player.
     * @param playerId the player ID
     */
    List<GameSession> findByPlayersId(Long playerId);
}