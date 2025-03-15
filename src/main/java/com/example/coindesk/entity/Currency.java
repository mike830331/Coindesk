package com.example.coindesk.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "currency")  // Explicitly define table name
public class Currency {

    @Id
    private String code;
    private String name;

    public Currency() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }


}
