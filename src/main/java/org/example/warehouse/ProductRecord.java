package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRecord {

    UUID productUUID;
    String productName;
    BigDecimal productPrice;
    Category productCategory;

    public ProductRecord(UUID productUUID, String productName, BigDecimal productPrice, Category category) {
        this.productUUID = productUUID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = category;
    }


    public Object uuid() {
        return null;
    }

    public Category category() {
        return null;
    }

    public boolean price() {
    }
}
