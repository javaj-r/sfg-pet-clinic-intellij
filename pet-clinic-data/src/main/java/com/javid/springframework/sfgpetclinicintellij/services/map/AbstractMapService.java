package com.javid.springframework.sfgpetclinicintellij.services.map;

import com.javid.springframework.sfgpetclinicintellij.model.BaseEntity;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

abstract class AbstractMapService<T extends BaseEntity, I extends Long> {

    Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(I id) {
        return map.get(id);
    }

    T save(T entity) {
        if (entity != null) {
            if (entity.getId() == null) {
                entity.setId(getNextId());
            }
            map.put(entity.getId(), entity);
        } else {
            throw new NullEntityException("entity cannot be null");
        }
        return entity;
    }

    void delete(T entity) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(entity));
    }

    void deleteById(I id) {
        map.remove(id);
    }

    private Long getNextId() {
        AtomicLong nextId = new AtomicLong();
        try {
            nextId.set((long) Collections.max(map.keySet()) + 1);
        } catch (NoSuchElementException e) {
            nextId.set(1L);
        }
        return nextId.get();
    }

    static class NullEntityException extends RuntimeException {
        public NullEntityException(String s) {
            super(s);
        }
    }
}
