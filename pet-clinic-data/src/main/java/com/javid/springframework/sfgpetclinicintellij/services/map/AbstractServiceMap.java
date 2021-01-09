package com.javid.springframework.sfgpetclinicintellij.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class AbstractServiceMap<T, I> {

    Map<I, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(I id) {
        return map.get(id);
    }

    T save(I id, T entity) {
        map.put(id, entity);

        return entity;
    }

    void delete(T entity) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(entity));
    }

    void deleteById(I id) {
        map.remove(id);
    }
}
