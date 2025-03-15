package com.example.coindesk.service;

import com.example.coindesk.dto.TransformedDataDTO;
import com.example.coindesk.entity.CoindeskResponse;
import com.example.coindesk.entity.Currency;
import com.example.coindesk.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CoindeskService {

    private final RestTemplate restTemplate;
    private final CurrencyService currencyService;

    private static final String COINDESK_API_URL = "https://kengp3.github.io/blog/coindesk.json";

    public List<TransformedDataDTO> getTransformedData() {
        CoindeskResponse response = fetchCoindeskData();
        List<Currency> currencies = currencyService.getAllCurrencies();
        return transformData(response, currencies);
    }

    private CoindeskResponse fetchCoindeskData() {
        ResponseEntity<CoindeskResponse> response = restTemplate.getForEntity(COINDESK_API_URL, CoindeskResponse.class);
        return response.getBody();
    }

    private List<TransformedDataDTO> transformData(CoindeskResponse coindeskData, List<Currency> currencyList) {
        String formattedTime = DateUtil.formatToCustomTime(coindeskData.getTime().getUpdatedISO());

        Map<String, String> currencyMap = currencyList.stream()
                .collect(Collectors.toMap(Currency::getCode, Currency::getName));

        return coindeskData.getBpi().values().stream()
                .map(currencyInfo -> new TransformedDataDTO(
                        formattedTime,
                        currencyInfo.getCode(),
                        currencyInfo.getRate(),
                        currencyMap.getOrDefault(currencyInfo.getCode(), "Unknown")
                ))
                .collect(Collectors.toList());
    }
}

