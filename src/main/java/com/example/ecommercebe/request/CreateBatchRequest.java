package com.example.ecommercebe.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class CreateBatchRequest {
        private String color;
        private String size;
        private int quantity;
        private Long pricePerBatch;
        private LocalDate productionDate;
        private LocalDate expirationDate;
}
