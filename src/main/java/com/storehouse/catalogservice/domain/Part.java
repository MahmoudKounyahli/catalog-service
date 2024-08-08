package com.storehouse.catalogservice.domain;

public record Part(
        String partNumber,
        String name,
        String description,
        Double price,
        int quantity,
        String category
) {}
