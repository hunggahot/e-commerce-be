package com.example.ecommercebe.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class UpdateBatchRequest {
        private String color;
        private String size;
        private Integer quantity;
        private Long pricePerBatch;
        private LocalDate productionDate;
        private LocalDate expirationDate;
}
