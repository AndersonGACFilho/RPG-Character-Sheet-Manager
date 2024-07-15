package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameRule;
import br.ufg.fullstack.rpg_character_sheet_manager.services.GameRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller for managing game rules.
 */
@RestController
@RequestMapping("/game-rules")
@CrossOrigin(origins = "*")
public class GameRuleController {

    @Autowired
    private GameRuleService gameRuleService;

    /**
     * Retrieves all game rules with pagination.
     * @param page the page number
     * @param size the number of game rules per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of GameRules with pagination
     */
    @GetMapping("/page")
    public ResponseEntity<Page<GameRule>> getAllGameRules(
            @RequestParam int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        Page<GameRule> gameRules =
                gameRuleService.getAllGameRules(page, size, sortBy, order);
        return ResponseEntity.ok(gameRules);
    }

    /**
     * Retrieves game rule by ID.
     * @param id the game rule ID
     * @return a GameRule
     */
    @GetMapping("/{id}")
    public ResponseEntity<GameRule> getGameRuleById(@PathVariable Long id) {
        GameRule gameRule = gameRuleService.getGameRuleById(id);
        return ResponseEntity.ok(gameRule);
    }

    /**
     * Creates a new game rule.
     * @param gameRule the GameRule to create
     * @return a ResponseEntity with the location of the created game rule
     */
    @PostMapping()
    public ResponseEntity<Void> createGameRule(
            @RequestBody GameRule gameRule)
    {
        GameRule createdGameRule =
                gameRuleService.createGameRule(gameRule);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdGameRule.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Updates an existing game rule.
     * @param gameRule the GameRule to update
     * @param id the game rule ID
     * @return a ResponseEntity with status 204 No Content
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGameRule(
        @RequestBody GameRule gameRule,
        @PathVariable Long id)
    {
        gameRuleService.updateGameRule(gameRule, id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a game rule by ID.
     * @param id the game rule ID
     * @return a ResponseEntity with status 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameRule(@PathVariable Long id) {
        gameRuleService.deleteGameRule(id);
        return ResponseEntity.noContent().build();
    }
}
