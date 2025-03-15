package com.example.coindesk.controller;

import com.example.coindesk.dto.TransformedDataDTO;
import com.example.coindesk.service.CoindeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coindesk")
@RequiredArgsConstructor
public class CoindeskController {

    private final CoindeskService coindeskService;

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchCoindeskData() {
        try {
            List<TransformedDataDTO> transformedData = coindeskService.getTransformedData();
            return ResponseEntity.ok(transformedData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching Coindesk data: " + e.getMessage());
        }
    }
}
