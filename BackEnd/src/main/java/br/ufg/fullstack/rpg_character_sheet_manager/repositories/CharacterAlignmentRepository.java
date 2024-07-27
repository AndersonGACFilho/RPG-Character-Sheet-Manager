package br.ufg.fullstack.rpg_character_sheet_manager.repositories;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterAlignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterAlignmentRepository extends JpaRepository<CharacterAlignment, Long> {
}