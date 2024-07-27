package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.Story;
import br.ufg.fullstack.rpg_character_sheet_manager.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stories")
public class StoryController {
    @Autowired
    private StoryService storyService;

    /**
     * Retrieves all stories.
     * @param page the page number
     * @param size the number of stories per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of StoryDTOs
     */
    @GetMapping("/page")
    public ResponseEntity<Page<Story>> getAllStories(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "24") Integer size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String order)
    {
        return ResponseEntity.ok(
                storyService.getAllStories(page, size, sortBy, order));
    }

    /**
     * Retrieves all stories by game session.
     * @param gameSessionId the game session ID
     * @param page the page number
     * @param size the number of stories per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of StoryDTOs
     */
    @GetMapping("/game-session/{gameSessionId}/page")
    public ResponseEntity<Page<Story>> getStoriesByGameSessionId(
            @PathVariable Long gameSessionId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order)
    {
        return ResponseEntity.ok(
                storyService.getStoriesByGameSessionId(gameSessionId, page,
                size, sortBy, order));
    }

    /**
     * Retrieves a story by ID.
     * @param id the story ID
     * @return a ResponseEntity containing the Story if found, or 404 Not
     * Found if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Story> getStoryById(@PathVariable Long id)
    {
        Story story = storyService.getStoryById(id);
        return ResponseEntity.ok(story);
    }

    /**
     * Creates a new story.
     * @param story the Story to create
     * @return a Story
     */
    @PostMapping
    public ResponseEntity<Void> createStory(
        @RequestBody Story story)
    {
        Story newStory = storyService.createStory(story);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates a story.
     * @param story the Story to update
     * @param id the story ID
     * @return a Story
     */
    @PutMapping("/{id}")
    public ResponseEntity<Story> updateStory(
        @RequestBody Story story,
        @PathVariable Long id)
    {
        Story updatedStory = storyService.updateStory(story, id);
        return ResponseEntity.ok(updatedStory);
    }

    /**
     * Deletes a story.
     * @param id the story ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable Long id)
    {
        storyService.deleteStory(id);
        return ResponseEntity.ok().build();
    }

}
