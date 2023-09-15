package com.example.ecommercebe.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateSupplierRequest {

    private String name;

    private String contactEmail;

    private String contactPhone;
}
