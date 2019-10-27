package com.company.gamestoreinvoiceserver.Dao;

import com.company.gamestoreinvoiceserver.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("invoiceItemDao")
public class InvoiceItemDaoTempImpl implements InvoiceItemDao {
    private JdbcTemplate jdbcTemplate;

    //Prepared Statements
    private static final String CREATE_INVOICE_ITEM_SQL =
            "INSERT INTO invoice_item (invoice_id, inventory_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

    private static final String GET_INVOICE_ITEM_SQL =
            "SELECT * FROM invoice_item WHERE invoice_item_id = ?";

    private static final String GET_INVOICE_ITEMS_BY_INVOICE_ID_SQL =
            "SELECT * FROM invoice_item WHERE invoice_id = ?";

    private static final String GET_ALL_INVOICE_ITEMS_SQL =
            "SELECT * FROM invoice_item";

    private static final String UPDATE_INVOICE_ITEM_SQL =
            "UPDATE invoice_item SET invoice_id = ?, inventory_id =?, quantity = ?, unit_price = ?, WHERE invoice_item_id = ?";

    private static final String DELETE_INVOICE_ITEM_SQL =
            "DELETE FROM invoice_item WHERE invoice_item_id = ?";


    @Autowired
    public InvoiceItemDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds an Invoice Item to the database
     * @param invoiceItem
     * @return InvoiceItem
     */
    @Override
    @Transactional
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(
                CREATE_INVOICE_ITEM_SQL,
                invoiceItem.getInvoice_id(),
                invoiceItem.getInventory_id(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnit_price()
        );

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        invoiceItem.setInvoice_item_id(id);
        return invoiceItem;
    }

    /**
     * Returns an Invoice Item from the database based on the id
     * @param invoice_item_id
     * @return InvoiceItem
     */
    @Override
    public InvoiceItem getInvoiceItem(Integer invoice_item_id) {
        try {
            return jdbcTemplate.queryForObject(GET_INVOICE_ITEM_SQL, this::mapRowToInvoiceItem, invoice_item_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Gets all the Invoice Items from the database in a list
     * @return List of Invoice Items
     */
    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(GET_ALL_INVOICE_ITEMS_SQL, this::mapRowToInvoiceItem);
    }

    /**
     * Updates an Invoice item from the database based on the id
     * @param invoiceItem
     */
    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
    jdbcTemplate.update(UPDATE_INVOICE_ITEM_SQL,
            invoiceItem.getInventory_id(),
            invoiceItem.getInvoice_id(),
            invoiceItem.getQuantity(),
            invoiceItem.getUnit_price(),
            invoiceItem.getInvoice_item_id());
    }

    /**
     * Deletes an invoice item from the database based on the id
     * @param invoice_item_id
     */
    @Override
    public void deleteInvoiceItem(Integer invoice_item_id) {
        jdbcTemplate.update(DELETE_INVOICE_ITEM_SQL, invoice_item_id);
    }

    @Override
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(Integer invoice_id) {
            return jdbcTemplate.query(GET_INVOICE_ITEMS_BY_INVOICE_ID_SQL, this::mapRowToInvoiceItem, invoice_id);
    }

    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_item_id(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoice_id(rs.getInt("invoice_id"));
        invoiceItem.setInventory_id(rs.getInt("inventory_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnit_price(rs.getBigDecimal("unit_price"));

        return invoiceItem;
    }
}
