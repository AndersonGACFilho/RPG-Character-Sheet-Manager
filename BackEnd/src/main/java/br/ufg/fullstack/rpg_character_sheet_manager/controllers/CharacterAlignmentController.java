package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterAlignment;
import br.ufg.fullstack.rpg_character_sheet_manager.services.CharacterAlignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller for managing character alignments.
 */
@RestController
@RequestMapping("/character-alignments")
@CrossOrigin(origins = "*")
public class CharacterAlignmentController {

    @Autowired
    private CharacterAlignmentService characterAlignmentService;

    /**
     * Retrieves all character alignments.
     * @param page the page number
     * @param size the number of character alignments per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of CharacterAlignments
     */
    @GetMapping
    public ResponseEntity<Page<CharacterAlignment>> getAllCharacterAlignments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order)
    {
        Page<CharacterAlignment> characterAlignments =
                characterAlignmentService.getAllCharacterAlignments(page, size,
                sortBy, order);
        return ResponseEntity.ok(characterAlignments);
    }

    /**
     * Retrieves character alignment by ID.
     * @param id the character alignment ID
     * @return a CharacterAlignment
     */
    @GetMapping("/{id}")
    public ResponseEntity<CharacterAlignment> getCharacterAlignmentById(
            @PathVariable Long id)
    {
        CharacterAlignment characterAlignment =
                characterAlignmentService.getCharacterAlignmentById(id);
        return ResponseEntity.ok(characterAlignment);
    }

    /**
     * Creates a new character alignment.
     * @param characterAlignment the CharacterAlignment to create
     * @return a ResponseEntity with the location of the created character alignment
     */
    @PostMapping
    public ResponseEntity<Void> createCharacterAlignment(
            @RequestBody CharacterAlignment characterAlignment)
    {
        CharacterAlignment createdCharacterAlignment =
                characterAlignmentService.createCharacterAlignment(characterAlignment);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdCharacterAlignment.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Updates an existing character alignment.
     * @param characterAlignment the CharacterAlignment to update
     * @param id the character alignment ID
     * @return a ResponseEntity with status 204 No Content
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCharacterAlignment(
            @RequestBody CharacterAlignment characterAlignment,
            @PathVariable Long id)
    {
        characterAlignmentService.updateCharacterAlignment(characterAlignment,
                id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a character alignment by ID.
     * @param id the character alignment ID
     * @return a ResponseEntity with status 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacterAlignment(@PathVariable Long id)
    {
        characterAlignmentService.deleteCharacterAlignment(id);
        return ResponseEntity.noContent().build();
    }
}
