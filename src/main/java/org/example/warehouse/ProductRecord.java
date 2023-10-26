package org.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class ProductRecord {

    private UUID uuid;
    private String name;
    private BigDecimal price;
    private Category category;

    // list containing all created categories?

    public ProductRecord(UUID uuid, String name, BigDecimal price, Category category) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.category = category;
    }



    /**returns this objects UUID*/
    public UUID uuid() {
        return this.uuid;
    }

    public Category category() {
        return this.category;
    }

    public Object price() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }





    @Override
    public String toString() {
        return "ProductRecord{" +
                "productUUID=" + uuid +
                ", productName='" + name + '\'' +
                ", productPrice=" + price +
                ", productCategory=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRecord that = (ProductRecord) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, price, category);
    }
}
