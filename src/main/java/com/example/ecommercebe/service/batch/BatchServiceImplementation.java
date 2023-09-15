package com.example.ecommercebe.service.batch;

import com.example.ecommercebe.entity.Batch;
import com.example.ecommercebe.exception.BatchException;
import com.example.ecommercebe.repository.BatchRepository;
import com.example.ecommercebe.request.CreateBatchRequest;
import com.example.ecommercebe.request.UpdateBatchRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BatchServiceImplementation implements BatchService{

    private final BatchRepository batchRepository;

    @Override
    public Batch createBatch(CreateBatchRequest request) throws BatchException {
        Batch batch = new Batch();
        batch.setColor(request.getColor());
        batch.setSize(request.getSize());
        batch.setQuantity(request.getQuantity());
        batch.setPricePerBatch(request.getPricePerBatch());
        batch.setProductionDate(request.getProductionDate());
        batch.setExpirationDate(request.getExpirationDate());

        return batchRepository.save(batch);
    }

    @Override
    public Batch updateBatch(Long batchId, UpdateBatchRequest request) throws BatchException {
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if (optionalBatch.isPresent()) {
            Batch batch = optionalBatch.get();

            if (request.getColor() != null) {
                batch.setColor(request.getColor());
            }
            if (request.getSize() != null) {
                batch.setSize(request.getSize());
            }
            if (request.getQuantity() != null) {
                batch.setQuantity(request.getQuantity());
            }
            if (request.getPricePerBatch() != null) {
                batch.setPricePerBatch(request.getPricePerBatch());
            }
            if (request.getProductionDate() != null) {
                batch.setProductionDate(request.getProductionDate());
            }
            if (request.getExpirationDate() != null) {
                batch.setExpirationDate(request.getExpirationDate());
            }
            return batchRepository.save(batch);
        } else {
            throw new BatchException("Batch not found with ID: " + batchId);
        }
    }

    @Override
    public void deleteBatch(Long batchId) throws BatchException {
        if (batchRepository.existsById(batchId)) {
            batchRepository.deleteById(batchId);
        } else {
            throw new BatchException("Batch not found with ID: " + batchId);
        }
    }

    @Override
    public Batch findBatchById(Long batchId) throws BatchException {

        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if (optionalBatch.isPresent()) {
            return optionalBatch.get();
        } else {
            throw new BatchException("Batch not found with ID: " + batchId);
        }
    }

    @Override
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }
}
