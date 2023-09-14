package com.example.ecommercebe.controller;

import com.example.ecommercebe.entity.Product;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.request.CreateProductRequest;
import com.example.ecommercebe.response.ApiResponse;
import com.example.ecommercebe.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req) {

        Product product = productService.createProduct(req);

        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {

        productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Product deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product req, @PathVariable Long productId) throws ProductException {

        Product product = productService.updateProduct(productId, req);

        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] req) {

        for (CreateProductRequest product : req) {
            productService.createProduct(product);
        }

        ApiResponse res = new ApiResponse();
        res.setMessage("Product created successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

//    public ResponseEntity<ApiResponse> importProductData(@RequestParam("file")MultipartFile file) {
//        try {
//            // Check if the uploaded file is an Excel file
//            if (!file.getOriginalFilename().endsWith(".xlsx")) {
//                ApiResponse res = new ApiResponse();
//                res.setMessage("Only Excel files (xlsx) are allowed");
//                res.setStatus(false);
//                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
//            }
//
//            // Parse the Excel file and import data
//
//        } catch (IOException e) {
//
//        }
//    }
}
