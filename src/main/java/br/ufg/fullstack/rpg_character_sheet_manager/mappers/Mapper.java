package br.ufg.fullstack.rpg_character_sheet_manager.mappers;

/**
 * Generic interface for converting between entities and DTOs.
 *
 * @param <E> the entity type
 * @param <D> the DTO type
 */
public interface Mapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}
