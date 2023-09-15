package com.example.ecommercebe.service.supplier;

import com.example.ecommercebe.entity.Supplier;
import com.example.ecommercebe.exception.SupplierException;
import com.example.ecommercebe.repository.SupplierRepository;
import com.example.ecommercebe.request.CreateSupplierRequest;
import com.example.ecommercebe.request.UpdateSupplierRequest;
import com.example.ecommercebe.service.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SupplierServiceImplementation implements SupplierService{

    private final SupplierRepository supplierRepository;
    private final ProductService productService;

    @Override
    public Supplier createSupplier(CreateSupplierRequest req) {
        Supplier supplier = new Supplier();
        supplier.setName(req.getName());
        supplier.setContactEmail(req.getContactEmail());
        supplier.setContactPhone(req.getContactPhone());

        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long supplierId, UpdateSupplierRequest req) throws SupplierException {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);

        if (optionalSupplier.isPresent()) {
            // Supplier found, update its attributes
            Supplier supplier = optionalSupplier.get();
            if (req.getName() != null) {
                supplier.setName(req.getName());
            }
            if (req.getContactEmail() != null) {
                supplier.setContactEmail(req.getContactEmail());
            }
            if (req.getContactPhone() != null) {
                supplier.setContactPhone(req.getContactPhone());
            }

            // Save the updated supplier
            return supplierRepository.save(supplier);
        } else {
            throw new SupplierException("Supplier not found with ID: " + supplierId);
        }
    }

    @Override
    public void deleteSupplier(Long supplierId) throws SupplierException {
        // Check if the supplier exists
        if (supplierRepository.existsById(supplierId)) {
            // Delete the supplier
            supplierRepository.deleteById(supplierId);
        } else {
            throw new SupplierException("Supplier not found with ID: " + supplierId);
        }
    }

    @Override
    public Supplier getSupplierById(Long supplierId) throws SupplierException {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);

        if (optionalSupplier.isPresent()) {
            return optionalSupplier.get();
        } else {
            throw new SupplierException("Supplier not found with ID: " + supplierId);
        }
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}
