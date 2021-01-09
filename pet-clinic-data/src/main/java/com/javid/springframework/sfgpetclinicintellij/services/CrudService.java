package com.javid.springframework.sfgpetclinicintellij.services;

import java.util.Set;

public interface CrudService<T, I> {

    Set<T> findAll();

    T findById(I id);

    T save(T entity);

    void delete(T entity);

    void deleteById(I id);
}
