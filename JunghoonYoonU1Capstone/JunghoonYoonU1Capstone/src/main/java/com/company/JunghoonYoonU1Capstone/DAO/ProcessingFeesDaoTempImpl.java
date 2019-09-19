package com.company.JunghoonYoonU1Capstone.DAO;

import com.company.JunghoonYoonU1Capstone.DTO.Processing_Fee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProcessingFeesDaoTempImpl implements ProcessingFeesDao {

    //Prepared Statements
    private static final String GET_PROCESSING_FEES_BY_TYPE_SQL =
        "SELECT * FROM processing_fee WHERE product_type = ?";

    private static final String UPDATE_PROCESSING_FEES_SQL =
        "UPDATE processing_fee SET fee WHERE product_type = ?";

    private static final String GET_ALL_PROCESSING_FEES_SQL =
        "SELECT * FROM processing_fee";

    private static final String ADD_PROCESSING_FEES_SQL =
        "INSERT INTO processing_fee (product_type, fee) VALUES (?, ?)";

    private static final String DELETE_PROCESSING_FEES_BY_TYPE_SQL =
        "DELETE FROM processing_fee where product_type = ?";



    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProcessingFeesDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Gets the processing fee by product type (if needed)
     * @param product_type
     * @return
     */
    @Override
    public Processing_Fee getProcessingFees(String product_type) {
       try {
           return jdbcTemplate.queryForObject(GET_PROCESSING_FEES_BY_TYPE_SQL, this::mapRowToProcessingFees, product_type);
       } catch (EmptyResultDataAccessException e) {
           return null;
       }
    }

    /**
     * Updates on the processing fees (if needed)
     * @param processing_fee
     */
    @Transactional
    @Override
    public void updateProcessingFees(Processing_Fee processing_fee) {
        jdbcTemplate.update(UPDATE_PROCESSING_FEES_SQL,
                processing_fee.getProduct_type(),
                processing_fee.getFee());
    }


    /**
     * Retrieves all the processing fees (if needed)
     * @return
     */
    @Override
    public List<Processing_Fee> getAllProcessingFees() {
        return jdbcTemplate.query(GET_ALL_PROCESSING_FEES_SQL, this::mapRowToProcessingFees);
    }

    /**
     * Adds a processing fee (if needed)
     * @param processing_fee
     * @return
     */
    @Transactional
    @Override
    public Processing_Fee addProcessingFees(Processing_Fee processing_fee) {
        jdbcTemplate.update(ADD_PROCESSING_FEES_SQL,
                processing_fee.getProduct_type(),
                processing_fee.getFee());

        return processing_fee;
    }

    /**
     * Deletes the processing fee (if needed)
     * @param product_type
     */
    @Transactional
    @Override
    public void deleteProcessingFees(String product_type) {
        jdbcTemplate.update(DELETE_PROCESSING_FEES_BY_TYPE_SQL, product_type);
    }

    public Processing_Fee mapRowToProcessingFees(ResultSet rs, int rowNum) throws SQLException {
        Processing_Fee processingFees = new Processing_Fee();
        processingFees.setProduct_type(rs.getString("product_type"));
        processingFees.setFee(rs.getBigDecimal("fee"));

        return processingFees;
    }
}
