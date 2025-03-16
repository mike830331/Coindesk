package com.example.coindesk.controller;

import com.example.coindesk.dto.TransformedDataDTO;
import com.example.coindesk.service.CoindeskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CoindeskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CoindeskService coindeskService;

    @InjectMocks
    private CoindeskController coindeskController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(coindeskController).build();  // Set up MockMvc
    }

    @Test
    void testFetchCoindeskData() throws Exception {
        // Mock API response
        List<TransformedDataDTO> mockResponse = Arrays.asList(
                new TransformedDataDTO("2024/09/02 07:07:20", "USD", "1000.00", "美元"),
                new TransformedDataDTO("2024/09/02 07:07:20", "EUR", "900.00", "歐元")
        );
        when(coindeskService.getTransformedData()).thenReturn(mockResponse);
        System.out.println("Mock response: " + coindeskService.getTransformedData());


        // Perform the request and verify response
        mockMvc.perform(get("/coindesk/fetch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].code").value("USD"))
                .andExpect(jsonPath("$[0].name").value("美元"))
                .andExpect(jsonPath("$[1].code").value("EUR"))
                .andExpect(jsonPath("$[1].name").value("歐元"));
    }
}
