package com.example.ecommercebe.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BatchRequest {
    private String batchNumber;
    private LocalDate manufacturingDate;
    private int quantity;
    private double pricePerUnit;
    private String supplierName;
    private Long productId;
}
