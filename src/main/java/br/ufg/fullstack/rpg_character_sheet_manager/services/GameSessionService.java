package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<GameSession> getAllGameSessions(Integer page) {
        // Fetch all GameSession entities from the repository
        PageRequest pageable = PageRequest.of(page, 10);
        return gameSessionRepository.findAll(pageable);
    }

    public GameSession getGameSessionById(Long id) {
        return gameSessionRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Game session not found"));
    }

    public List<GameSession> getGameSessionsByMasterId(Long masterId) {
        return gameSessionRepository.findByMasterId(masterId);
    }

    public GameSession createGameSession(GameSession gameSession, Long masterId) {
        User master = userService.getUserById(masterId);
        master.addSessionAsMaster(gameSession);
        gameSession.setMaster(master);
        userService.updateUser(master);
        return gameSessionRepository.save(gameSession);
    }

    public GameSession updateGameSession(GameSession gameSession) {
        return gameSessionRepository.save(gameSession);
    }

    public void deleteGameSession(Long id) {
        gameSessionRepository.deleteById(id);
    }


}
