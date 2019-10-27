package com.company.gamestoreinvoiceserver.Dao;

import com.company.gamestoreinvoiceserver.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("invoiceDao")
public class InvoiceDaoTempImpl implements InvoiceDao {
    private JdbcTemplate jdbcTemplate;

    //Prepared Statements
    private static final String CREATE_INVOICE_SQL =
            "INSERT INTO invoice(customer_id, purchase_date) VALUES (?, ?)";

    private static final String GET_INVOICE_SQL =
            "SELECT * FROM invoice WHERE invoice_id = ?";

    private static final String GET_ALL_INVOICE_SQL =
            "SELECT * FROM invoice";

    private static final String GET_INVOICE_BY_CUSTOMER_ID_SQL =
            "SELECT * FROM invoice WHERE customer_id = ?";

    private static final String UPDATE_INVOICE_SQL =
            "UPDATE invoice SET customer_id = ?, purchase_date = ?, WHERE invoice_id = ?";

    private static final String DELETE_INVOICE_SQL =
            "DELETE FROM invoice WHERE invoice_id = ?";

    @Autowired
    public InvoiceDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds an Invoice to the database
     * @param invoice
     * @return Invoice
     */
    @Override
    @Transactional
    public Invoice createInvoice(Invoice invoice) {
        jdbcTemplate.update(
                CREATE_INVOICE_SQL,
                invoice.getCustomer_id(),
                invoice.getPurchase_date()
        );

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        invoice.setInvoice_id(id);
        return invoice;
    }


    /**
     * Returns an invoice from the database based on the id
     * @param invoice_id
     * @return Invoice
     */
    @Override
    public Invoice getInvoice(Integer invoice_id) {
        try {
            return jdbcTemplate.queryForObject(GET_INVOICE_SQL, this::mapRowToInvoice, invoice_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Gets all the invoices from the database
     * @return List of Invoice
     */
    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(GET_ALL_INVOICE_SQL, this::mapRowToInvoice);
    }

    /**
     * Gets the invoice by the customer id
     * @param customer_id
     * @return invoice
     */
    @Override
    public Invoice getInvoiceByCustomerId(Integer customer_id) {
        try {
            return jdbcTemplate.queryForObject(GET_INVOICE_BY_CUSTOMER_ID_SQL, this::mapRowToInvoice, customer_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Updates an invoice from the database based on the id
     * @param invoice
     */
    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL,
                invoice.getCustomer_id(),
                invoice.getPurchase_date(),
                invoice.getInvoice_id());
    }

    /**
     * Deletes an invoice from the database based on the id.
     * @param invoice_id
     */
    @Override
    public void deleteInvoice(Integer invoice_id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoice_id);
    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(rs.getInt("invoice_id"));
        invoice.setCustomer_id(rs.getInt("customer_id"));
        invoice.setPurchase_date(rs.getDate("purchase_date").toLocalDate());

        return invoice;
    }
}
