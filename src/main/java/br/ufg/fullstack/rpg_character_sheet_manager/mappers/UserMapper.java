package br.ufg.fullstack.rpg_character_sheet_manager.mappers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.dtos.UserDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting User entities to UserDTOs and vice versa.
 */
@Component
public class UserMapper implements Mapper<User, UserDTO> {

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the User entity
     * @return the UserDTO
     */
    @Override
    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        // Map User entity properties to UserDTO
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO the UserDTO
     * @return the User entity
     */
    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        // Map UserDTO properties to User entity
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
