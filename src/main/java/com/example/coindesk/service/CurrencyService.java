package com.example.coindesk.service;

import com.example.coindesk.entity.Currency;
import com.example.coindesk.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency createCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency updateCurrency(String code, Currency newCurrency) {
        return currencyRepository.findById(code)
                .map(existingCurrency -> {
                    existingCurrency.setName(newCurrency.getName());
                    return currencyRepository.save(existingCurrency);
                })
                .orElseThrow(() -> new RuntimeException("Currency not found"));
    }

    public void deleteCurrency(String code) {
        currencyRepository.deleteById(code);
    }
}

