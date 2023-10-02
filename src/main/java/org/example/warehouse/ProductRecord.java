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


    //todo should return a product with this id
    public Object uuid() {
        return null;
    }

    // todo return a category
    public Category category() {
        return null;
    }

    // todo return a price or something
    public boolean price() {
        return true;
    }
}
