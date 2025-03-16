package com.example.coindesk.controller;

import com.example.coindesk.entity.Currency;
import com.example.coindesk.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurrencyRepository currencyRepository;

    @BeforeEach
    void setup() {
        currencyRepository.deleteAll();
    }

    @Test
    public void testCreateCurrency() throws Exception {
        String jsonRequest = "{\"code\": \"USD\", \"name\": \"美元\"}";

        mockMvc.perform(post("/currencies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("USD"))
                .andExpect(jsonPath("$.name").value("美元"));
    }

    @Test
    public void testGetAllCurrencies() throws Exception {
        currencyRepository.save(new Currency("USD", "美元"));

        mockMvc.perform(get("/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].code").value("USD"))
                .andExpect(jsonPath("$[0].name").value("美元"));
    }

    @Test
    public void testUpdateCurrency() throws Exception {
        currencyRepository.save(new Currency("USD", "美元"));

        String jsonRequest = "{\"name\": \"美金\"}";

        mockMvc.perform(put("/currencies/USD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("美金"));
    }

    @Test
    public void testDeleteCurrency() throws Exception {
        currencyRepository.save(new Currency("USD", "美元"));

        mockMvc.perform(delete("/currencies/USD"))
                .andExpect(status().isNoContent());

        assertFalse(currencyRepository.findById("USD").isPresent());
    }
}