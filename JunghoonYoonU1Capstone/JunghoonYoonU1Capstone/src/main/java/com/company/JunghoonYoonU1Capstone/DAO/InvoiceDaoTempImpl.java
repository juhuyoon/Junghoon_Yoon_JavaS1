package com.company.JunghoonYoonU1Capstone.DAO;

import com.company.JunghoonYoonU1Capstone.DTO.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoTempImpl implements InvoiceDao{

    //Prepared Statements
    private static final String ADD_INVOICE_SQL =
            "INSERT INTO invoice(name, street, city, state, zipcode, item_type, item_id, unit_price, quantity, subtotal, tax, processing_fee, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_INVOICE_SQL =
            "SELECT * FROM invoice WHERE invoice_id = ?";

    private static final String GET_ALL_INVOICE_SQL =
            "SELECT * FROM invoice";

    private static final String UPDATE_INVOICE_SQL =
            "UPDATE invoice SET name = ?, street = ?, city = ?, state = ?, zipcode = ?, item_type = ?, item_id = ?, unit_price = ?, quantity = ?, subtotal = ?, tax = ?, processing_fee = ?, total = ? WHERE invoice_id = ?";

    private static final String DELETE_INVOICE_SQL =
            "DELETE FROM invoice WHERE invoice_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds an Invoice to the database
     * @param invoice
     * @return
     */
    @Override
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(ADD_INVOICE_SQL,
                invoice.getName(),
                invoice.getStreet(),
                invoice.getCity(),
                invoice.getState(),
                invoice.getZipcode(),
                invoice.getItem_type(),
                invoice.getItem_id(),
                invoice.getUnit_price(),
                invoice.getQuantity(),
                invoice.getSubtotal(),
                invoice.getTax(),
                invoice.getProcessing_fee(),
                invoice.getTotal());
        int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
        invoice.setInvoice_id(id);

        return invoice;
    }

    /**
     * Retrieves an Invoice object from the database
     * @param invoice_id
     * @return
     */
    @Override
    public Invoice getInvoice(Integer invoice_id) {
        try {
            return jdbcTemplate.queryForObject(GET_INVOICE_SQL, this::mapRowByInvoice, invoice_id);
        } catch (EmptyResultDataAccessException e)  {
            return null;
        }
    }

    /**
     * Retrieves all the invoices in the database
     * @return
     */
    @Override
    public List<Invoice> getAllInvoice() {
        return jdbcTemplate.query(GET_ALL_INVOICE_SQL, this::mapRowByInvoice);
    }

    /**
     * Updates the invoice from the database
     * @param invoice
     */
    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL,
                invoice.getName(),
                invoice.getState(),
                invoice.getCity(),
                invoice.getState(),
                invoice.getZipcode(),
                invoice.getItem_type(),
                invoice.getItem_id(),
                invoice.getUnit_price(),
                invoice.getQuantity(),
                invoice.getSubtotal(),
                invoice.getTax(),
                invoice.getProcessing_fee(),
                invoice.getTotal(),
                invoice.getInvoice_id());
    }

    /**
     * Deletes an invoice from the database
     * @param invoice_id
     */
    @Override
    public void deleteInvoice(Integer invoice_id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoice_id);
    }

private Invoice mapRowByInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(rs.getInt("invoice_id"));
        invoice.setName(rs.getString("name"));
        invoice.setStreet(rs.getString("street"));
        invoice.setCity(rs.getString("city"));
        invoice.setState(rs.getString("state"));
        invoice.setZipcode(rs.getString("zipcode"));
        invoice.setItem_type(rs.getString("item_type"));
        invoice.setItem_id(rs.getInt("item_id"));
        invoice.setUnit_price(rs.getBigDecimal("unit_price"));
        invoice.setQuantity(rs.getInt("quantity"));
        invoice.setSubtotal(rs.getBigDecimal("subtotal"));
        invoice.setTax(rs.getBigDecimal("tax"));
        invoice.setProcessing_fee(rs.getBigDecimal("processing_fee"));
        invoice.setTotal(rs.getBigDecimal("total"));

        return invoice;
}
}
