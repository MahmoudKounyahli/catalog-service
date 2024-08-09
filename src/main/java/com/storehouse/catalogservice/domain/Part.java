package com.storehouse.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Part(
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
        @NotNull(message = "The part quantity must be defined.")
        @Positive(
                message = "The part quantity must be greater than zero."
        )
        int quantity,
        @NotBlank(message = "The part category must be defined.")
        String category
) {}
