package com.company.JunghoonYoonGameStore.DAO;

import com.company.JunghoonYoonGameStore.DTO.Sales_Tax_Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SalesTaxRateDaoTempImpl implements SalesTaxRate_Dao {

    //Prepared Statements
    private static final String GET_SALES_TAX_RATES_BY_STATE_SQL =
            "SELECT * FROM sales_tax_rate WHERE state = ?";

    private static final String GET_ALL_SALES_TAX_RATES_SQL =
            "SELECT * FROM sales_tax_rate";

    private static final String UPDATE_SALES_TAX_RATES_BY_STATE_SQL =
            "UPDATE sales_tax_rate SET rate = ? WHERE state = ?";

    private static final String ADD_SALES_TAX_RATES_SQL =
            "INSERT INTO sales_tax_rate (state, rate) VALUES (?, ?)";

    private static final String DELETE_SALES_TAX_RATES_BY_STATE_SQL =
            "DELETE FROM sales_tax_rate where state = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SalesTaxRateDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Gets the sales tax rate by state
     * @param state
     * @return
     */
    @Override
    public Sales_Tax_Rate getSalesTaxRate(String state) {
        try {
            return jdbcTemplate.queryForObject(GET_SALES_TAX_RATES_BY_STATE_SQL, this::mapRowToSalesTaxRate, state);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Updates the sales tax rate (if needed)
     * @param sales_tax_rate
     */
    @Transactional
    @Override
    public void updateSalesTaxRate(Sales_Tax_Rate sales_tax_rate) {
        jdbcTemplate.update(UPDATE_SALES_TAX_RATES_BY_STATE_SQL,
                sales_tax_rate.getState(),
                sales_tax_rate.getRate()
        );
    }

    /**
     * Adds a Sales Tax Rate (if needed by state)
     * @param sales_tax_rate
     * @return
     */
    @Transactional
    @Override
    public Sales_Tax_Rate addSalesTaxRate(Sales_Tax_Rate sales_tax_rate) {
        jdbcTemplate.update(ADD_SALES_TAX_RATES_SQL,
                sales_tax_rate.getState(),
                sales_tax_rate.getRate());

        return sales_tax_rate;
    }

    /**
     * Deletes the Sales Tax Rate by State (if needed)
     * @param state
     */
    @Transactional
    @Override
    public void deleteSalesTaxRate(String state) {
        jdbcTemplate.update(DELETE_SALES_TAX_RATES_BY_STATE_SQL, state);
    }

    /**
     * Retrieves all the sales tax rates(if needed)
     * @return
     */
    @Override
    public List<Sales_Tax_Rate> getAllSalesTaxRate() {
        return jdbcTemplate.query(GET_ALL_SALES_TAX_RATES_SQL, this::mapRowToSalesTaxRate);
    }

    public Sales_Tax_Rate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException {
        Sales_Tax_Rate salesTaxRate = new Sales_Tax_Rate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));

        return salesTaxRate;
    }

}
