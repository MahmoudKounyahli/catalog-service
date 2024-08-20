package com.storehouse.catalogservice.web;

import com.storehouse.catalogservice.domain.Part;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class PartJsonTests {

    @Autowired
    private JacksonTester<Part> json;

    @Test
    void testSerialize() throws Exception {
        var part = Part.of("0123456789", "name", "description", 5.5, "company",4, "category");
        var jsonContent = json.write(part);
        assertThat(jsonContent).extractingJsonPathStringValue("@.partNumber")
                .isEqualTo(part.partNumber());
        assertThat(jsonContent).extractingJsonPathStringValue("@.name")
                .isEqualTo(part.name());
        assertThat(jsonContent).extractingJsonPathStringValue("@.category")
                .isEqualTo(part.category());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(part.price());
        assertThat(jsonContent).extractingJsonPathStringValue("@.company")
                .isEqualTo(part.company());
        assertThat(jsonContent).extractingJsonPathStringValue("@.description")
                .isEqualTo(part.description());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.quantity")
                .isEqualTo(part.quantity());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                	"partNumber": "0123456789",
                    "name": "name",
                    "description": "description",
                    "price": 5.5,
                    "company": "company",
                    "quantity": 4,
                    "category": "category"
                }
                """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(Part.of("0123456789", "name", "description", 5.5, "company", 4, "category"));
    }
}
