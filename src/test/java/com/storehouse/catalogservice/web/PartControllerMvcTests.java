package com.storehouse.catalogservice.web;

import com.storehouse.catalogservice.exception.PartNotFoundException;
import com.storehouse.catalogservice.rest.PartController;
import com.storehouse.catalogservice.service.PartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(PartController.class)
public class PartControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartService partService;

    @Test
    void whenGetPartNotExistingThenShouldReturn404() throws Exception {
        String partNumber = "0123456789";
        given(partService.viewPartDetails(partNumber)).willThrow(PartNotFoundException.class);
        mockMvc
                .perform(get("/parts/" + partNumber))
                .andExpect(status().isNotFound());
    }
}
