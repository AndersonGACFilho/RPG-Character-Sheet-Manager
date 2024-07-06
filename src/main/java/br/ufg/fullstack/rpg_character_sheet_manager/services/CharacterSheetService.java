package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterSheet;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.CharacterSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterSheetService {
    @Autowired
    private CharacterSheetRepository characterSheetRepository;
    @Autowired
    private UserService userService;

    /**
     * Get all character sheets by page
     * @param page
     * @return Page<CharacterSheet> with all character sheets
     */
    public Page<CharacterSheet> getAllCharacterSheets(Integer page) {
        // Create a pageable object to return only 10 results per page
        PageRequest pageable = PageRequest.of(page, 10);

        // Return all character sheets
        return characterSheetRepository.findAll(pageable);
    }

    /**
     * Get a character sheet by ID
     * @param id
     * @return CharacterSheet with the given ID
     */
    public CharacterSheet getCharacterSheetById(Long id) {
        Optional<CharacterSheet> characterSheet =
                characterSheetRepository.findById(id);
        return characterSheet.orElseThrow(() ->
                new ResourceNotFoundException("Character sheet not found"));
    }

    /**
     * Create a new character sheet
     * @param characterSheet
     * @return CharacterSheet created
     */
    public CharacterSheet createCharacterSheet(CharacterSheet characterSheet, Long userId) {
        // Get the user by ID
        User user = userService.getUserById(userId);
        // Add the character sheet to the user's list of character sheets
        user.addCharacterSheet(characterSheet);
        // Set the user of the character sheet
        characterSheet.setOwner(user);
        // Save the user
        userService.updateUser(user, userId);
        return characterSheetRepository.save(characterSheet);
    }

    /**
     * Update a character sheet
     * @param characterSheet
     * @param id
     */
    public void updateCharacterSheet(CharacterSheet characterSheet, Long id) {
        // Find the character sheet by ID
        getCharacterSheetById(id);
        // Update the character sheet
        characterSheet.setId(id);
        // Save the updated character sheet
        characterSheetRepository.save(characterSheet);
    }

    /**
     * Delete a character sheet
     * @param id
     */
    public void deleteCharacterSheet(Long id) {
        try {
            // Delete the character sheet by ID
            characterSheetRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // If the character sheet was not found, throw a DataIntegrityViolationException
            throw new DataIntegrityViolationException("Character sheet not found");
        }
    }

    /**
     * Get all character sheets by user
     * @param userId
     * @param page
     * @return Page<CharacterSheet> with all character sheets
     */
    public Page<CharacterSheet> getCharacterSheetsByUserId(Long userId, Integer page) {
        // Create a pageable object to return only 10 results per page
        Pageable pageable = PageRequest.of(page, 10);
        // Return all character sheets by user
        return characterSheetRepository.findByOwnerId(userId, pageable);
    }
}
