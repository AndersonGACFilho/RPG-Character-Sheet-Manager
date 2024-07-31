package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterSheet;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.CharacterSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterSheetService {
    @Autowired
    private CharacterSheetRepository characterSheetRepository;
    @Autowired
    @Lazy
    private UserService userService;

    /**
     * Get all character sheets by page
     *
     * @param page the page number
     * @param size the number of items per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return Page<CharacterSheet> with all character sheets
     */
    public Page<CharacterSheet> getAllCharacterSheets(Integer page,
        Integer size, String sortBy, String order)
    {
        // Create a pageable object to return only 10 results per page
        PageRequest pageable = PageRequest.of(page, size,
            Sort.Direction.fromString(order), sortBy);
        // Return all character sheets
        return characterSheetRepository.findAll(pageable);
    }

    /**
     * Get all character sheets by user
     * @param page the page number
     * @param size the number of items per page
     * @param sortBy the field to sort by a character sheet field
     * @param order the sort order
     * @return Page<CharacterSheet> with all character sheets
     */
    public Page<CharacterSheet> getCharacterSheetsByUserId(Integer page, Integer size, String sortBy, String order) {
        // Create a pageable object to return only 10 results per page
        Pageable pageable = PageRequest.of(page, 10);
        // Get the user ID from the authenticated user
        Long userId = userService.getUser().getId();
        // Return all character sheets by user
        return characterSheetRepository.findByOwnerId(userId, pageable);
    }

    /**
     * Get a character sheet by ID
     * @param id the character sheet ID
     * @return CharacterSheet with the given ID
     */
    public CharacterSheet getCharacterSheetById(Long id) {
        Optional<CharacterSheet> characterSheet =
                characterSheetRepository.findById(id);
        return characterSheet.orElseThrow(() ->
                new ResourceNotFoundException("Character sheet not found"));
    }

    /**
     * Create a new character sheet at the logged user
     * @param characterSheet the character sheet to create
     * @return CharacterSheet created
     */
    public CharacterSheet createCharacterSheet(CharacterSheet characterSheet) {
        // Get the user by ID
        User user = userService.getUser();
        // Add the character sheet to the user's list of character sheets
        user.addCharacterSheet(characterSheet);
        // Set the user of the character sheet
        characterSheet.setOwner(user);
        // Save the user
        userService.updateUser(user);
        return characterSheetRepository.save(characterSheet);
    }

    /**
     * Update a character sheet
     * @param characterSheet the character sheet to update
     * @param id the character sheet ID
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
     * @param id the character sheet ID
     */
    public void deleteCharacterSheet(Long id) {
        try {
            // Get the character sheet by ID
            CharacterSheet characterSheet = getCharacterSheetById(id);
            // Get the owner of the character sheet
            User owner = characterSheet.getOwner();
            // Remove the character sheet from the owner
            owner.removeCharacterSheet(characterSheet);
            // Save the owner
            userService.updateUser(owner, owner.getId());
            // Remove the owner from the character sheet
            characterSheet.setOwner(null);
            // Delete the character sheet
            characterSheetRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // If the character sheet was not found, throw a DataIntegrityViolationException
            throw new DataIntegrityViolationException("Character sheet not found");
        }
    }
}
