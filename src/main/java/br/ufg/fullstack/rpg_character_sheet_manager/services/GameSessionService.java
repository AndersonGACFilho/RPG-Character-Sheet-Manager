package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.*;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.GameSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSessionService {
    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    @Lazy
    private CharacterSheetService characterSheetService;
    @Autowired
    @Lazy
    private GameRuleService gameRuleService;
    @Autowired
    @Lazy
    private StoryService storyService;
    @Autowired
    @Lazy
    private CharacterAlignmentService characterAlignmentService;

    /**
     * Retrieves all game sessions.
     *
     * @param page the page number
     * @param size the number of game sessions per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of GameSessionDTOs
     */
    public Page<GameSession> getAllGameSessions(Integer page, Integer size,
        String sortBy, String order)
    {
        // Fetch all GameSession entities from the repository
        PageRequest pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.fromString(order), sortBy));
        return gameSessionRepository.findAll(pageable);
    }

    /**
     * Retrieves a game session by ID.
     * @param id the game session ID
     * @return a GameSession
     */
    public GameSession getGameSessionById(Long id) {
        return gameSessionRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Game session not found"));
    }

    /**
     * Retrieves all game sessions by master.
     * @param masterId the master ID
     * @param page the page number
     * @return a list of GameSessionDTOs
     */
    public Page<GameSession> getGameSessionsByMasterId(Long masterId, Integer page)
    {
        // Fetch all GameSession entities by master from the repository
        PageRequest pageable = PageRequest.of(page, 10);
        // Return the list of game sessions by master
        return gameSessionRepository.findByMasterId(masterId, pageable);
    }

    /**
     * Create a new game session.
     * @param gameSession the game session to create
     * @param masterId the ID of the master user
     * @return the created GameSession
     */
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

    /**
     * Update a game session.
     * @param gameSession the game session to update
     * @param id the game session ID
     */
    public void updateGameSession(GameSession gameSession, Long id)
    {
        // Find the game session by ID
        getGameSessionById(id);
        // Update the game session
        gameSession.setId(id);
        // Save the updated game session
        gameSessionRepository.save(gameSession);
    }

    /**
     * Delete a game session.
     * @param id the game session ID
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteGameSession(Long id)
    {
        try
        {
            // Remove all players from the game session
            GameSession gameSession = getGameSessionById(id);
            // Remove the game session from the master's list of game sessions
            User master = gameSession.getMaster();
            // Remove the character sheets from the game session
            List<CharacterSheet> characterSheets =
                    gameSession.getCharacterSheets();
            // Remove the character sheets from the owner users
            // and delete the character sheets
            for (CharacterSheet characterSheet : characterSheets)
            {
                characterSheetService.deleteCharacterSheet(
                        characterSheet.getId());
                gameSession.removeCharacterSheet(characterSheet);
            }
            // Remove the players from the game session
            List<User> players = gameSession.getPlayers();
            for (User player : players)
            {
                userService.removePlayerFromGameSession(player.getId(), id);
                gameSession.removePlayer(player);
            }
            // Get game rules from the game session
            // and delete the game rules
            List<GameRule> gameRules = gameSession.getGameRules();
            for (GameRule gameRule : gameRules)
            {
                gameRuleService.deleteGameRule(gameRule.getId());
                gameSession.removeGameRule(gameRule);
            }
            // Get game session stories from the game session
            // and delete the game session stories
            List<Story> gameSessionStories =
                    gameSession.getStories();
            for (Story gameSessionStory : gameSessionStories)
            {
                storyService.deleteStory(gameSessionStory.getId());
                gameSession.removeStory(gameSessionStory);
            }
            // Remove alignment from the game session
            List<CharacterAlignment> alignments =
                    gameSession.getCharacterAlignments();
            for (CharacterAlignment alignment : alignments)
            {
                characterAlignmentService.deleteCharacterAlignment(
                        alignment.getId());
                gameSession.removeCharacterAlignment(alignment);
            }
            // Remove the game session from the master
            if (master != null) {
                master.removeSessionAsMaster(gameSession);
                // Save the master
                userService.updateUser(master, master.getId());
                gameSession.setMaster(null);
            }
            // Delete the game session
            gameSessionRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e)
        {
            // If the game session was not found, throw a
            // DataIntegrityViolationException
            throw new DataIntegrityViolationException("Game session not found");
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("There was an error deleting " +
                "the game session");
        }
    }

    /**
     * Remove a player from a game session.
     * @param id the player ID
     * @param gameSessionId the game session ID
     */
    @Transactional(rollbackOn = Exception.class)
    public void removePlayerFromGameSession(Long id, Long gameSessionId)
    {
        try
        {
            // Find the user by ID
            User user = userService.getUserById(id);
            // Find the game session by ID
            GameSession gameSession = getGameSessionById(gameSessionId);
            // Remove the user from the game session
            gameSession.removePlayer(user);
            // Save the game session
            updateGameSession(gameSession, gameSessionId);
        }
        catch (DataIntegrityViolationException e)
        {
            // If the user was not found, throw a DataIntegrityViolationException
            throw new DataIntegrityViolationException(" When removing player " +
                "from game session, occurred a DataIntegrityViolationException");
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("User not found");
        }

    }
}
