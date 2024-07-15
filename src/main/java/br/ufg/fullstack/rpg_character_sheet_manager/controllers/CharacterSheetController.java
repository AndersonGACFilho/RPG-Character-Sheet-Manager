package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterSheet;
import br.ufg.fullstack.rpg_character_sheet_manager.services.CharacterSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller for managing character sheets.
 */
@RestController
@RequestMapping("/character-sheets")
@CrossOrigin(origins = "*")
public class CharacterSheetController {

    @Autowired
    private CharacterSheetService characterSheetService;

    /**
     * Retrieves all character sheets with pagination.
     * @param page the page number
     * @param size the number of character sheets per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of CharacterSheets with pagination
     */
    @GetMapping("/page")
    public ResponseEntity<Page<CharacterSheet>> getAllCharacterSheets(
            @RequestParam int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        Page<CharacterSheet> characterSheets =
                characterSheetService.getAllCharacterSheets(page, size, sortBy,
                order);
        return ResponseEntity.ok(characterSheets);
    }

    /**
     * Retrieves character sheets by user ID with pagination.
     * @param userId the user ID
     * @param page the page number
     * @param size the number of character sheets per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of CharacterSheets with pagination
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<CharacterSheet>> getCharacterSheetsByUserId(
            @PathVariable Long userId,
            @RequestParam int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        Page<CharacterSheet> characterSheets = characterSheetService.getCharacterSheetsByUserId(userId, page, size, sortBy, order);
        return ResponseEntity.ok(characterSheets);
    }

    /**
     * Retrieves character sheet by ID.
     * @param id the character sheet ID
     * @return a CharacterSheet
     */
    @GetMapping("/{id}")
    public ResponseEntity<CharacterSheet> getCharacterSheetById(@PathVariable Long id) {
        CharacterSheet characterSheet = characterSheetService.getCharacterSheetById(id);
        return ResponseEntity.ok(characterSheet);
    }

    /**
     * Creates a new character sheet.
     * @param characterSheet the CharacterSheet to create
     * @param userId the user ID
     * @return a ResponseEntity with the location of the created character sheet
     */
    @PostMapping("/user/{userId}")
    public ResponseEntity<Void> createCharacterSheet(
            @RequestBody CharacterSheet characterSheet,
            @PathVariable Long userId) {
        CharacterSheet createdCharacterSheet = characterSheetService.createCharacterSheet(characterSheet, userId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdCharacterSheet.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Updates an existing character sheet.
     * @param characterSheet the CharacterSheet to update
     * @param id the character sheet ID
     * @return a ResponseEntity with status 204 No Content
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCharacterSheet(@RequestBody CharacterSheet characterSheet, @PathVariable Long id) {
        characterSheetService.updateCharacterSheet(characterSheet, id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a character sheet by ID.
     * @param id the character sheet ID
     * @return a ResponseEntity with status 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacterSheet(@PathVariable Long id) {
        characterSheetService.deleteCharacterSheet(id);
        return ResponseEntity.noContent().build();
    }
}
