package com.storehouse.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Part(
        @Id
        Long id,
        @NotBlank(message = "The part number must be defined.")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "The number format must be valid."
        )
        String partNumber,
        @NotBlank(
                message = "The part number must be defined."
        )
        String name,
        @NotBlank(message = "The part description must be defined.")
        String description,
        @NotNull(message = "The part price must be defined.")
        @Positive(
                message = "The part price must be greater than zero."
        )
        Double price,
        String company,
        @NotNull(message = "The part quantity must be defined.")
        @Positive(
                message = "The part quantity must be greater than zero."
        )
        int quantity,
        @NotBlank(message = "The part category must be defined.")
        String category,
        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate,
        @Version
        int version
) {
        public static Part of(String  partNumber, String name, String description, Double price, String company, int quantity, String category) {
                return new Part(null, partNumber, name, description, price, company, quantity, category, null, null, 0);
        }
}
