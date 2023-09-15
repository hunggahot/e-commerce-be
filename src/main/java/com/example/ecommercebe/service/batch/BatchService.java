package com.example.ecommercebe.service.batch;

import com.example.ecommercebe.entity.Batch;
import com.example.ecommercebe.exception.BatchException;
import com.example.ecommercebe.request.CreateBatchRequest;
import com.example.ecommercebe.request.UpdateBatchRequest;

import java.util.List;

public interface BatchService {
    Batch createBatch(CreateBatchRequest request) throws BatchException;

    Batch updateBatch(Long batchId, UpdateBatchRequest request) throws BatchException;

    void deleteBatch(Long batchId) throws BatchException;

    Batch findBatchById(Long batchId) throws BatchException;

    List<Batch> getAllBatches();
}
