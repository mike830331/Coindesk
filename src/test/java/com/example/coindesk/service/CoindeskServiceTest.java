package com.example.coindesk.service;

import com.example.coindesk.dto.TransformedDataDTO;
import com.example.coindesk.entity.CoindeskResponse;
import com.example.coindesk.entity.Currency;
import com.example.coindesk.entity.CurrencyInfo;
import com.example.coindesk.entity.TimeInfo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CoindeskServiceTest {

    @InjectMocks
    private CoindeskService coindeskService;

    @Test
    public void testTransformData() {
        // Mock Coindesk API 回應
        CoindeskResponse response = new CoindeskResponse();
        response.setTime(new TimeInfo("2024-09-02T07:07:20+00:00"));

        Map<String, CurrencyInfo> bpi = new HashMap<>();
        bpi.put("USD", new CurrencyInfo("USD", "&#36", "1000.00", "United States Dollar", 1000.00));
        bpi.put("EUR", new CurrencyInfo("EUR", "&euro;", "900.00", "Euro", 900.00));
        response.setBpi(bpi);

        // Mock DB currencyList
        List<Currency> currencyList = Arrays.asList(
                new Currency("USD", "美元"),
                new Currency("EUR", "歐元")
        );

        // Test transformData
        List<TransformedDataDTO> result = coindeskService.transformData(response, currencyList);

        // verify response
        assertEquals(2, result.size());
        assertEquals("美元", result.get(1).getName());
        assertEquals("1000.00", result.get(1).getRate());
        assertEquals("歐元", result.get(0).getName());
        assertEquals("900.00", result.get(0).getRate());
    }
}
