package com.example.coindesk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransformedDataDTO {
    private String updatedTime;
    private String code;
    private String rate;
    private String name;
}

