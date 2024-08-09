package com.storehouse.catalogservice;

import com.storehouse.catalogservice.domain.Part;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PartValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var part = new Part(
                "0123456789",
                "name",
                "description",
                50.0,
                5,
                "category");
        Set<ConstraintViolation<Part>> violations = validator.validate(part);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenPartNumberDefinedButIncorrectThenValidationFails() {
        var part = new Part(
                "a0123456789",
                "name",
                "description",
                50.0,
                5,
                "category");
        Set<ConstraintViolation<Part>> violations = validator.validate(part);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The number format must be valid.");

    }
}
