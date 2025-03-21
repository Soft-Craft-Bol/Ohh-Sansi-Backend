package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import java.util.*;

public interface IDatabaseRepository<T, ID> {
    T save(T entity);
    List<T> saveAll(Iterable<T> entities);
    Optional<T> findById(ID id);
    boolean existsById(ID id);
    List<T> findAll();
    List<T> findAllById(Iterable<ID> ids);
    long count();
    void deleteById(ID id);
    void delete(T entity);
    void deleteAllById(Iterable<? extends ID> ids);
    void deleteAll(Iterable<? extends T> entities);
    void deleteAll();
}