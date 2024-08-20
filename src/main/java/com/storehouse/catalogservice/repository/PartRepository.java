package com.storehouse.catalogservice.repository;

import com.storehouse.catalogservice.domain.Part;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PartRepository extends CrudRepository<Part, Long> {

    Iterable<Part> findByCategory(String category);
    Optional<Part> findByPartNumber(String partNumber);
    boolean existsByPartNumber(String partNumber);
    @Modifying
    @Transactional
    @Query("delete from part where part_number = :partNumber")
    void deleteByPartNumber(String partNumber);
}
