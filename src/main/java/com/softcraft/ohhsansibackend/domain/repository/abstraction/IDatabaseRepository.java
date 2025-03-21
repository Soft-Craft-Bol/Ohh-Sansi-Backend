package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import java.util.*;

public interface IDatabaseRepository<T, ID> {
    default T save(T entity) {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default List<T> saveAll(Iterable<T> entities) {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default Optional<T> findById(ID id) {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default boolean existsById(ID id) {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default List<T> findAll() {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default List<T> findAllById(Iterable<ID> ids) {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default long count() {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default void deleteById(ID id) {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default void delete(T entity) {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default void deleteAllById(Iterable<? extends ID> ids) {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default void deleteAll(Iterable<? extends T> entities) {
        throw new UnsupportedOperationException("sin implementacion");
    }
    default void deleteAll() {
        throw new UnsupportedOperationException("sin implementacion");
    }
}