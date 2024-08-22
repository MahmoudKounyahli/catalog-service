package com.storehouse.catalogservice.domain;

import com.storehouse.catalogservice.config.DataConfig;
import com.storehouse.catalogservice.repository.PartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class PartRepositoryJdbcTests {
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findPartByPartNumberWhenExisting() {
        var partNumber = "1123456789";
        var part = Part.of(partNumber, "name", "description", 5.5, "company", 5, "category");
        jdbcAggregateTemplate.insert(part);
        Optional<Part> actualPart = partRepository.findByPartNumber(partNumber);

        assertThat(actualPart).isPresent();
        assertThat(actualPart.get().partNumber()).isEqualTo(part.partNumber());
    }
}
