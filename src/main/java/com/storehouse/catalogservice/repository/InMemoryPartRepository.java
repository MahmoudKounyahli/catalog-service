package com.storehouse.catalogservice.repository;

import com.storehouse.catalogservice.domain.Part;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPartRepository implements PartRepository {
    private static final Map<String, Part> parts = new ConcurrentHashMap<>();
    @Override
    public Iterable<Part> findAll() {
        return parts.values();
    }

    @Override
    public Iterable<Part> findByCategory(String category) {
        return parts.values().stream().filter(part -> Objects.equals(part.category(), category)).toList();
    }

    @Override
    public Optional<Part> findByPartNumber(String partNumber) {
        return existsByPartNumber(partNumber) ? Optional.of(parts.get(partNumber)) : Optional.empty();
    }

    @Override
    public boolean existsByPartNumber(String partNumber) {
        return Objects.nonNull(parts.get(partNumber));
    }

    @Override
    public Part save(Part part) {
        parts.put(part.partNumber(), part);
        return part;
    }

    @Override
    public void deleteByPartNumber(String partNumber) {
        parts.remove(partNumber);
    }
}
