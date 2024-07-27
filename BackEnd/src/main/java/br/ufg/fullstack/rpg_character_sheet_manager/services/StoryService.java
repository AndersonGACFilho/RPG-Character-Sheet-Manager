package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.Story;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Service class for managing stories.
 * This class handles the business logic related to stories.
 */
@Service
public class StoryService {
    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    @Lazy
    private GameSessionService gameSessionService;

    /**
     * Retrieves all stories.
     * @param page the page number
     * @param size the number of stories per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of Stories
     */
    public Page<Story> getAllStories(Integer page, Integer size,
                                     String sortBy, String order)
    {
        // Create a pageable object
        PageRequest pageable = PageRequest.of(page, size,
                Sort.Direction.fromString(order), sortBy);
        // Return all stories
        return storyRepository.findAll(pageable);
    }

    /**
     * Retrieves all stories by game session.
     * @param gameSessionId the game session ID
     * @param page the page number
     * @param size the number of stories per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of Stories
     */
    public Page<Story> getStoriesByGameSessionId(Long gameSessionId,
         Integer page, Integer size, String sortBy, String order)
    {
        // Create a pageable object
        PageRequest pageable = PageRequest.of(page, size,
                Sort.Direction.fromString(order), sortBy);
        // Return all stories by game session ID
        return storyRepository.findByGameSessionId(gameSessionId, pageable);
    }

    /**
     * Retrieves a story by ID.
     * @param id the story ID
     * @return a Story
     */
    public Story getStoryById(Long id) {
        return storyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Story not found"));
    }

    /**
     * Create a new story.
     * @param story the story to create
     * @return the created Story
     */
    public Story createStory(Story story) {
        // Find game session by ID
        GameSession gameSession = gameSessionService.getGameSessionById(
                story.getGameSession().getId());
        // Add the story to the game session
        gameSession.addStory(story);
        // Set the game session of the story
        story.setGameSession(gameSession);
        // Save the game session
        gameSessionService.updateGameSession(gameSession,
                gameSession.getId());
        return storyRepository.save(story);
    }

    /**
     * Update a story.
     * @param story the story to update
     * @param storyId the ID of the story to update
     * @return the updated Story
     */
    public Story updateStory(Story story, Long storyId) {
        Story existingStory = getStoryById(storyId);
        story.setId(existingStory.getId());
        return storyRepository.save(story);
    }

    /**
     * Delete a story.
     * @param storyId the ID of the story to delete
     * the related game session
     */
    public void deleteStory(Long storyId) {
        // Find the story by ID
        Story existingStory = getStoryById(storyId);
        // Find the game session by ID
        GameSession gameSession = existingStory.getGameSession();
        if (gameSession != null) {
            // Remove the story from the game session
            gameSession.removeStory(existingStory);
            // Save the game session
            gameSessionService.updateGameSession(gameSession,
                    gameSession.getId());
        }
        // Remove the game session from the story
        existingStory.setGameSession(null);
        // Delete the story
        storyRepository.delete(existingStory);
    }
}

