package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GameSessionService {
    @Autowired
    private GameSessionRepository gameSessionRepository;
    @Autowired
    private UserService userService;

    /**
     * Retrieves all game sessions.
     * @param page the page number
     * @return a list of GameSessionDTOs
     */
    public Page<GameSession> getAllGameSessions(Integer page)
    {
        // Fetch all GameSession entities from the repository
        PageRequest pageable = PageRequest.of(page, 10);
        return gameSessionRepository.findAll(pageable);
    }

    public GameSession getGameSessionById(Long id) {
        return gameSessionRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Game session not found"));
    }

    public Page<GameSession> getGameSessionsByMasterId(Long masterId, Integer page)
    {
        // Fetch all GameSession entities by master from the repository
        PageRequest pageable = PageRequest.of(page, 10);
        // Return the list of game sessions by master
        return gameSessionRepository.findByMasterId(masterId, pageable);
    }

    public GameSession createGameSession(GameSession gameSession, Long masterId)
    {
        // Find the master by ID
        User master = userService.getUserById(masterId);
        // Add the game session to the master's list of game sessions
        master.addSessionAsMaster(gameSession);
        // Set the master of the game session
        gameSession.setMaster(master);
        // Save the master
        userService.updateUser(master, masterId);
        // Save the game session
        return gameSessionRepository.save(gameSession);
    }

    public void updateGameSession(GameSession gameSession, Long id)
    {
        // Find the game session by ID
        getGameSessionById(id);
        // Update the game session
        gameSession.setId(id);
        // Save the updated game session
        gameSessionRepository.save(gameSession);
    }

    public void deleteGameSession(Long id)
    {
        try
        {
            // Delete the game session by ID
            gameSessionRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e)
        {
            // If the game session was not found, throw a DataIntegrityViolationException
            throw new DataIntegrityViolationException("Game session not found");
        }
    }


}
