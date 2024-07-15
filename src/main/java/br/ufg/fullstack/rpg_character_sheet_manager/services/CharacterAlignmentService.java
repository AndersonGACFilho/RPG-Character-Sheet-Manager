package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterAlignment;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterSheet;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.CharacterAlignmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing character alignments.
 */
@Service
public class CharacterAlignmentService {

    @Autowired
    private CharacterAlignmentRepository characterAlignmentRepository;
    @Autowired
    @Lazy
    private CharacterSheetService characterSheetService;

    /**
     * Retrieves all character alignments.
     * @param page the page number
     * @param size the number of character alignments per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of CharacterAlignments
     */
    public Page<CharacterAlignment> getAllCharacterAlignments(
            int page,
            int size,
            String sortBy,
            String order)
    {
        PageRequest pageRequest = PageRequest.of(page, size,
                Sort.Direction.fromString(order), sortBy);
        return characterAlignmentRepository.findAll(pageRequest);
    }

    /**
     * Retrieves character alignment by ID.
     * @param id the character alignment ID
     * @return a CharacterAlignment
     */
    public CharacterAlignment getCharacterAlignmentById(Long id)
    {
        return characterAlignmentRepository.findById(id)
            .orElseThrow(() ->
            new ResourceNotFoundException("Character alignment not found"));
    }

    /**
     * Creates a new character alignment.
     * @param characterAlignment the CharacterAlignment to create
     * @return the created CharacterAlignment
     */
    public CharacterAlignment createCharacterAlignment(
            CharacterAlignment characterAlignment)
    {
        return characterAlignmentRepository.save(characterAlignment);
    }

    /**
     * Updates an existing character alignment.
     * @param characterAlignment the CharacterAlignment to update
     * @param id the character alignment ID
     */
    public void updateCharacterAlignment(
            CharacterAlignment characterAlignment,
            Long id)
    {
        CharacterAlignment existingCharacterAlignment =
                getCharacterAlignmentById(id);
       characterAlignment.setId(existingCharacterAlignment.getId());
        characterAlignmentRepository.save(characterAlignment);
    }

    /**
     * Deletes a character alignment by ID.
     * @param id the character alignment ID
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteCharacterAlignment(Long id) {
        try {
            CharacterAlignment characterAlignment =
                    getCharacterAlignmentById(id);
            // Remove the character alignment from the characters
            List<CharacterSheet> characters =
                    characterAlignment.getCharacters();
            for (CharacterSheet character : characters) {
                character.setAlignment(null);
                characterSheetService.updateCharacterSheet(character,
                        character.getId());
            }
            //Get game sessions that reference this character alignment
            GameSession gameSessions = characterAlignment.getGameSession();
            //Remove the character alignment from the game sessions
            gameSessions.removeCharacterAlignment(characterAlignment);
            characterAlignment.setGameSession(null);
            characterAlignmentRepository.delete(characterAlignment);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(
                    "Cannot delete character alignment");
        }
        catch (Exception e) {
            throw new ResourceNotFoundException(
                    "Character alignment not found");
        }
    }
}
