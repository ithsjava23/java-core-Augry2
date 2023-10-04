package org.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class ProductRecord {

    UUID productUUID;
    String productName;
    BigDecimal productPrice;
    Category productCategory;

    // list containing all created categories?

    public ProductRecord(UUID productUUID, String productName, BigDecimal productPrice, Category category) {
        this.productUUID = productUUID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = category;
    }



    /**returns this objects UUID*/
    public UUID uuid() {
        return this.productUUID;
    }

    public Category category() {
        return this.productCategory;
    }

    public Object price() {
        return this.productPrice;
    }


    @Override
    public String toString() {
        return "ProductRecord{" +
                "productUUID=" + productUUID +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productCategory=" + productCategory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRecord that = (ProductRecord) o;
        return Objects.equals(productUUID, that.productUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productUUID);
    }
}
