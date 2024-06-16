package br.ufg.fullstack.rpg_character_sheet_manager.repositories;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entities.
 * Provides CRUD operations for User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by username.
     *
     * @param username the username
     * @return the User entity
     */
    User findByUsername(String username);
}
