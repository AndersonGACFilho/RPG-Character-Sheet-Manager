package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import br.ufg.fullstack.rpg_character_sheet_manager.services.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller for managing game sessions.
 */
@RestController
@RequestMapping("/game-sessions")
@CrossOrigin(origins = "*")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;

    /**
     * Retrieves all game sessions by authenticated master with pagination.
     * @param page the page number
     * @return a list of GameSessions with pagination
     */
    @GetMapping("/master/page/{page}")
    public ResponseEntity<Page<GameSession>> getGameSessionsByMasterId(
            @PathVariable int page)
    {
        Page<GameSession> gameSessions =
                gameSessionService.getGameSessionsByMaster(page);
        return ResponseEntity.ok(gameSessions);
    }

    /**
     * Retrieves game session by ID.
     * @param id the game session ID
     * @return a GameSession
     */
    @GetMapping("/{id}")
    public ResponseEntity<GameSession> getGameSessionById(
            @PathVariable Long id)
    {
        GameSession gameSession = gameSessionService.getGameSession(id);
        return ResponseEntity.ok(gameSession);
    }

    /**
     * Creates a new game session of the authenticated user.
     * @param gameSession the GameSession to create
     * @return a ResponseEntity with the location of the created game session
     */
    @PostMapping("/master/")
    public ResponseEntity<Void> createGameSession(
            @RequestBody GameSession gameSession)
    {
        GameSession createdGameSession =
                gameSessionService.createGameSession(gameSession);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdGameSession.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Retrieves all game sessions with pagination.
     * @param page the page number
     * @param size the number of game sessions per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of GameSessions with pagination
     */
    @GetMapping("/page/{page}")
    public ResponseEntity<Page<GameSession>> getAllGameSessions(
            @PathVariable int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order)
    {
        Page<GameSession> allGameSessions =
            gameSessionService.getAllGameSessions(page, size, sortBy, order);
        return ResponseEntity.ok(allGameSessions);
    }

    /**
     * Updates an existing game session.
     * @param gameSession the GameSession to update
     * @param id the game session ID
     * @return a ResponseEntity with status 204 No Content
     */
    @PutMapping("/master/{id}")
    public ResponseEntity<Void> updateGameSession(
            @RequestBody GameSession gameSession,
            @PathVariable Long id)
    {
        gameSessionService.updateGameSession(gameSession, id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a game session by ID.
     * @param id the game session ID
     * @return a ResponseEntity with status 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameSession(@PathVariable Long id)
    {
        gameSessionService.deleteGameSession(id);
        return ResponseEntity.noContent().build();
    }
}
