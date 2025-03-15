package com.example.coindesk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyInfo {
    private String code;
    private String symbol;
    private String rate;
    private String description;

    @JsonProperty("rate_float")  // JSON key is "rate_float", map it correctly
    private double rateFloat;

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRateFloat() {
        return rateFloat;
    }

    public void setRateFloat(double rateFloat) {
        this.rateFloat = rateFloat;
    }
}
