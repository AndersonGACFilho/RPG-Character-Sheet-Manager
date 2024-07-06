package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import br.ufg.fullstack.rpg_character_sheet_manager.services.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/game-sessions")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;

    /**
     * Retrieves all game sessions, by master, with pagination.
     * @param masterId the master ID
     * @return a list of GameSessions with pagination
     */
    @GetMapping("master/{masterId}/page/{page}")
    public ResponseEntity<Page<GameSession>> getGameSessionsByMasterId(@PathVariable Long masterId,
       @PathVariable Integer page)
    {
        // Call the service to get all game sessions by master
        Page<GameSession> gameSessions =
                gameSessionService.getGameSessionsByMasterId(masterId,page);
        // Return the list of game sessions with pagination and HTTP status code 200
        return ResponseEntity.ok(gameSessions);
    }

    /**
     * Retrieves game session by ID.
     * @param id the game session ID
     * @return a GameSession
    */
    @GetMapping("/{id}")
    public ResponseEntity<GameSession> getGameSessionById(@PathVariable Long id) {
        // Call the service to get the game session by ID
        GameSession gameSession = gameSessionService.getGameSessionById(id);
        // Return the game session with HTTP status code 200
        return ResponseEntity.ok(gameSession);
    }

    /**
     * Creates a new game session.
     * @param gameSession the GameSession to create
     * @param masterId the master ID
     * @return a GameSession
     */
    @PostMapping("master/{masterId}")
    public ResponseEntity<Void> createGameSession(@RequestBody GameSession gameSession,
        @PathVariable Long masterId) {
        // Call the service to create a new game session
        GameSession createdGameSession = gameSessionService.createGameSession(gameSession, masterId);
        // Get the URI of the created game session
        URI gameSessionURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdGameSession.getId()).toUri();
        // Return the created game session with HTTP status code 201
        return ResponseEntity.created(gameSessionURI).build();
    }

    /**
     * Retrieves all game sessions with pagination.
     * @param page the page number
     * @return a list of GameSession
     */
    @GetMapping("/page/{page}")
    public ResponseEntity<Page<GameSession>> getAllGameSessions(@PathVariable int page) {
        // Call the service to get all game sessions with pagination
        Page<GameSession> allGameSessions = gameSessionService.getAllGameSessions(page);
        // Return the list of game sessions with pagination and HTTP status code 200
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing game session.
     * @param gameSession the GameSession to update
     * @return a ResponseEntity with status 204 No Content
     */
    @PutMapping("master/{masterId}/{id}")
    public ResponseEntity<Void> updateGameSession(@RequestBody GameSession gameSession,
        @PathVariable Long id)
    {
        // Call the service to update the game session
        gameSessionService.updateGameSession(gameSession, id);
        // Return the updated game session with HTTP status code No Content (204)
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a game session by ID.
     * @param id the game session ID
     */
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteGameSession(@PathVariable Long id) {
        // Call the service to delete the game session
        gameSessionService.deleteGameSession(id);
        // Return HTTP status code 200 OK
        return ResponseEntity.noContent().build();
    }
}
