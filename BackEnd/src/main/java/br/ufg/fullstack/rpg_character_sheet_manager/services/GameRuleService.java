package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameRule;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.GameRuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Service class for managing game rules.
 * This class handles the business logic related to game rules.
 */
@Service
public class GameRuleService {

    @Autowired
    private GameRuleRepository gameRuleRepository;

    @Autowired
    private GameSessionService gameSessionService;

    /**
     * Retrieves all game rules.
     * @param page the page number
     * @param size the number of game rules per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of GameRules
     */
    public Page<GameRule> getAllGameRules(Integer page, Integer size,
        String sortBy, String order) {
        // Create a pageable object to return only 10 results per page
        PageRequest pageable = PageRequest.of(page, size,
                Sort.Direction.fromString(order), sortBy);
        // Return all game rules
        return gameRuleRepository.findAll(pageable);
    }

    /**
     * Retrieves all game rules related to a game session.
     * @param page the page number
     * @param size the number of game rules per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of GameRules
     */
    public Page<GameRule> getGameRulesByGameSessionId(Long gameSessionId,
        Integer page, Integer size, String sortBy, String order) {
        // Create a pageable object to return only 10 results per page
        PageRequest pageable = PageRequest.of(page, size,
                Sort.Direction.fromString(order), sortBy);
        // Return all game rules by game session
        return gameRuleRepository.findByGameSessionId(gameSessionId, pageable);
    }

    /**
     * Retrieves a game rule by ID.
     * @param id the game rule ID
     * @return a GameRule
     */
    public GameRule getGameRuleById(Long id) {
        return gameRuleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Game rule not found"));
    }

    /**
     * Create a new game rule.
     * @param gameRule the game rule to create
     * @return the created GameRule
     */
    public GameRule createGameRule(GameRule gameRule) {
        // Save the game rule
        return gameRuleRepository.save(gameRule);
    }

    /**
     * Update a game rule.
     * @param gameRule the game rule to update
     * @param id the ID of the game rule to update
     * @return the updated GameRule
     */
    public GameRule updateGameRule(GameRule gameRule, Long id) {
        GameRule existingGameRule = getGameRuleById(id);
        gameRule.setId(id);
        return gameRuleRepository.save(gameRule);
    }

    /**
     * Delete a game rule.
     * @param id the ID of the game rule to delete
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteGameRule(Long id) {
        try{
            // Find the game rule by ID
            GameRule gameRule = getGameRuleById(id);
            // Remove the game rule from the list of game rules
            GameSession gameSession = gameRule.getGameSession();
            // Remove the game rule from the game session
            gameSession.removeGameRule(gameRule);
            // Save the game session
            gameSessionService.updateGameSession(gameSession,
                    gameSession.getId());
            // Delete the game rule
            gameRuleRepository.delete(gameRule);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Game rule not found");
        }
    }
}
