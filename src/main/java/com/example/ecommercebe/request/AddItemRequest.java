package com.example.ecommercebe.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AddItemRequest {

    private Long productId;
    private String size;
    private int quantity;
    private Long price;
}
