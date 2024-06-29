package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import br.ufg.fullstack.rpg_character_sheet_manager.services.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-sessions")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;

    /**
     * Retrieves all game sessions, by master.
     * @param masterId the master ID
     * @return a list of GameSessionDTOs
     */
    @GetMapping("master/{masterId}")
    public List<GameSession> getGameSessionsByMasterId(@PathVariable Long masterId) {
        // Call the service to get all game sessions by master
        return gameSessionService.getGameSessionsByMasterId(masterId);
    }

    /**
     * Retrieves game session by ID.
     * @param id the game session ID
     * @return a GameSession
    */
    @GetMapping("/{id}")
    public GameSession getGameSessionById(@PathVariable Long id) {
        // Call the service to get the game session by ID
        return gameSessionService.getGameSessionById(id);
    }

    /**
     * Creates a new game session.
     * @param gameSession the GameSession to create
     * @param masterId the master ID
     * @return a GameSession
     */
    @PostMapping("master/{masterId}")
    public GameSession createGameSession(@RequestBody GameSession gameSession, @PathVariable Long masterId) {
        // Call the service to create a new game session
        return gameSessionService.createGameSession(gameSession, masterId);
    }

    /**
     * Retrieves all game sessions with pagination.
     * @param page the page number
     * @return a list of GameSessionDTOs
     */
    @GetMapping("/page/{page}")
    public Page<GameSession> getAllGameSessions(@PathVariable int page) {
        // Call the service to get all game sessions with pagination
        return gameSessionService.getAllGameSessions(page);
    }

    /**
     * Updates an existing game session.
     * @param gameSession the GameSession to update
     * @return a GameSession
     */
    @PutMapping
    public GameSession updateGameSession(@RequestBody GameSession gameSession) {
        // Call the service to update the game session
        return gameSessionService.updateGameSession(gameSession);
    }

    /**
     * Deletes a game session by ID.
     * @param id the game session ID
     */
    @DeleteMapping("/{id}")
    public void deleteGameSession(@PathVariable Long id) {
        // Call the service to delete the game session by ID
        gameSessionService.deleteGameSession(id);
    }
}
