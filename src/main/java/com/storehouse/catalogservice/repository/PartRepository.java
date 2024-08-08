package com.storehouse.catalogservice.repository;

import com.storehouse.catalogservice.domain.Part;

import java.util.Optional;

public interface PartRepository {
    Iterable<Part> findAll();
    Iterable<Part> findByCategory(String category);
    Optional<Part> findByPartNumber(String partNumber);
    boolean existsByPartNumber(String partNumber);
    Part save(Part part);
    void deleteByPartNumber(String partNumber);
}
