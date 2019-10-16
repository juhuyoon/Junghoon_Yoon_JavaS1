package com.company.JunghoonYoonGameStore.DAO;

import com.company.JunghoonYoonGameStore.DTO.Sales_Tax_Rate;

import java.util.List;

public interface SalesTaxRate_Dao {
    Sales_Tax_Rate getSalesTaxRate(String state);

    Sales_Tax_Rate addSalesTaxRate(Sales_Tax_Rate sales_tax_rate);

    List<Sales_Tax_Rate> getAllSalesTaxRate();

    void deleteSalesTaxRate(String state);

    void updateSalesTaxRate(Sales_Tax_Rate sales_tax_rate);
}
