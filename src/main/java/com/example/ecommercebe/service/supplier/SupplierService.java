package com.example.ecommercebe.service.supplier;

import com.example.ecommercebe.entity.Supplier;
import com.example.ecommercebe.exception.SupplierException;
import com.example.ecommercebe.request.CreateSupplierRequest;
import com.example.ecommercebe.request.UpdateSupplierRequest;

import java.util.List;

public interface SupplierService {
    Supplier createSupplier(CreateSupplierRequest req);

    Supplier updateSupplier(Long supplierId, UpdateSupplierRequest req) throws SupplierException;

    void deleteSupplier(Long supplierId) throws SupplierException;

    Supplier getSupplierById(Long supplierId) throws SupplierException;

    List<Supplier> getAllSuppliers();
}
