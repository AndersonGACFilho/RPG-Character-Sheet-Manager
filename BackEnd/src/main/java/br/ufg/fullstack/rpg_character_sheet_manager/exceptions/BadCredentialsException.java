package br.ufg.fullstack.rpg_character_sheet_manager.exceptions;

/**
 * This class represents an exception thrown when the credentials are invalid.
 */
public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String message) {
        super(message);
    }
}
