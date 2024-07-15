package br.ufg.fullstack.rpg_character_sheet_manager.repositories;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {

    /**
     * Retrieves all stories by game session ID.
     * @param gameSessionId the game session ID
     * @param pageable the pageable object
     * @return a list of Stories
     */
    Page<Story> findByGameSessionId(Long gameSessionId, Pageable pageable);
}