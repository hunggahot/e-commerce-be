package com.example.ecommercebe.request;

import com.example.ecommercebe.entity.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class CreateProductRequest {

    private String title;

    private String description;

    private Long price;

    private Long discountedPrice;

    private int discountPercent;

    private int quantity;

    private String brand;

    private String color;

    private Set<Size> size = new HashSet<>();

    private String imageUrl;

    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;

}
