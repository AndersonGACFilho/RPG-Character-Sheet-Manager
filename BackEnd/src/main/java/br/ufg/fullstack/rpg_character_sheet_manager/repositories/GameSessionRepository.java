package br.ufg.fullstack.rpg_character_sheet_manager.repositories;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, Long> {

    /**
     * Finds a game sessions by master.
     * @param masterId the master ID
     * @param pageable the pagination information
     */
    Page<GameSession> findByMasterId(Long masterId, Pageable pageable);

    /**
     * Finds a game sessions by player.
     * @param playerId the player ID
     */
    List<GameSession> findByPlayersId(Long playerId);
}