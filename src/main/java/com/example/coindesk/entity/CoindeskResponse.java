package com.example.coindesk.entity;

import java.util.Map;

public class CoindeskResponse {
    private TimeInfo time;
    private String disclaimer;
    private String chartName;
    private Map<String, CurrencyInfo> bpi; // Store currencies as a Map (USD, GBP, EUR)

    // Getters and Setters
    public TimeInfo getTime() { return time; }
    public void setTime(TimeInfo time) { this.time = time; }

    public String getDisclaimer() { return disclaimer; }
    public void setDisclaimer(String disclaimer) { this.disclaimer = disclaimer; }

    public String getChartName() { return chartName; }
    public void setChartName(String chartName) { this.chartName = chartName; }

    public Map<String, CurrencyInfo> getBpi() { return bpi; }
    public void setBpi(Map<String, CurrencyInfo> bpi) { this.bpi = bpi; }
}
