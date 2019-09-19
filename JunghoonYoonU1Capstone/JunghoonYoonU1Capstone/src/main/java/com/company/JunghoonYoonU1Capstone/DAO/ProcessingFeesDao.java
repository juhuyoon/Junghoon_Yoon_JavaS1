package com.company.JunghoonYoonU1Capstone.DAO;

import com.company.JunghoonYoonU1Capstone.DTO.Processing_Fee;

import java.util.List;

public interface ProcessingFeesDao {
    Processing_Fee getProcessingFees(String product_type);

    List<Processing_Fee> getAllProcessingFees();

    Processing_Fee addProcessingFees(Processing_Fee processing_fee);

    void updateProcessingFees(Processing_Fee processing_fee);

    void deleteProcessingFees(String product_type);

}
