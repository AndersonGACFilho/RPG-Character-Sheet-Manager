package br.ufg.fullstack.rpg_character_sheet_manager.repositories;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterSheet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterSheetRepository extends JpaRepository<CharacterSheet, Long> {
    /**
     * Find all character sheets by owner ID
     * @param userId ID of the owner
     * @param pageable PageRequest object
     * @return Page<CharacterSheet> with all character sheets
     */
    Page<CharacterSheet> findByOwnerId(Long userId, Pageable pageable);
}