package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterSheet;
import br.ufg.fullstack.rpg_character_sheet_manager.services.CharacterSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController("/character-sheets")
public class CharacterSheetController {

    @Autowired
    private CharacterSheetService characterSheetService;

    /**
     * Retrieves all character sheets by page.
     * @param page the page number
     * @return a list of CharacterSheets with pagination
     * @see CharacterSheetService
     */
    @GetMapping("/page/{page}")
    public ResponseEntity<Page<CharacterSheet>> getAllCharacterSheets(@PathVariable Integer page) {
        // Call the service to get all character sheets
        Page<CharacterSheet> characterSheets = characterSheetService.getAllCharacterSheets(page);
        // Return the list of character sheets
        return ResponseEntity.ok(characterSheets);
    }

    /**
     * Retrieves a character sheet by ID.
     * @param id the character sheet ID
     * @return a CharacterSheet
     * @see CharacterSheetService
     */
    @GetMapping("/{id}")
    public ResponseEntity<CharacterSheet> getCharacterSheetById(@PathVariable Long id) {
        // Call the service to get the character sheet by ID
        CharacterSheet characterSheet = characterSheetService.getCharacterSheetById(id);
        // Return the character sheet
        return ResponseEntity.ok(characterSheet);
    }

    /**
     * Creates a new character sheet.
     * @param characterSheet the CharacterSheet to create
     * @param userId the user ID
     * @return a ResponseEntity containing the created character sheet
     * @see CharacterSheetService
     */
    @PostMapping("user/{userId}")
    public ResponseEntity<Void> createCharacterSheet(@RequestBody CharacterSheet characterSheet,
        @PathVariable Long userId) {
        // Call the service to create a new character sheet
        CharacterSheet createdCharacterSheet =
                characterSheetService.createCharacterSheet(characterSheet, userId);
        // Get the URI of the created character sheet
        URI characterSheetURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdCharacterSheet.getId()).toUri();
        // Return the created character sheet
        return ResponseEntity.created(characterSheetURI).build();
    }

    /**
     * Updates a character sheet.
     * @param characterSheet the CharacterSheet to update
     * @return a ResponseEntity containing the updated character sheet
     * @see CharacterSheetService
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCharacterSheet(@PathVariable Long id,
        @RequestBody CharacterSheet characterSheet) {
        // Call the service to update the character sheet
        characterSheetService.updateCharacterSheet(characterSheet, id);
        // Return the updated character sheet
        return ResponseEntity.noContent().build();
    }

}
