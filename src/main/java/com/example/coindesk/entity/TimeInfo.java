package com.example.coindesk.entity;

public class TimeInfo {
    private String updated;
    private String updatedISO;
    private String updateduk;

    // Getters and Setters
    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdatedISO() {
        return updatedISO;
    }

    public void setUpdatedISO(String updatedISO) {
        this.updatedISO = updatedISO;
    }

    public String getUpdateduk() {
        return updateduk;
    }

    public void setUpdateduk(String updateduk) {
        this.updateduk = updateduk;
    }

    public TimeInfo(String updatedISO) {
        this.updatedISO = updatedISO;
    }
}
