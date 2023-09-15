package com.example.ecommercebe.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateCategoryRequest {

    private String name;
    private Long parentCategoryId;
}
