package com.scheduling.system.Domain.parsers;

/**
 * This interface will be for convert an entity to Dto or Dto to entity.
 * Created by Rafael Encinas.
 */
public interface IParser<E, D> {
    D parserEntityToDto(E entity);
    E parserDtoToEntity(D dto);
}