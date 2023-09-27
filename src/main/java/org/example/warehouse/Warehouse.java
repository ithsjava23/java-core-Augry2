package org.example.warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Warehouse {
    /**list that contains all the products in the store*/
    List<ProductRecord> listOfProducts = new ArrayList<>();

    //todo 2 private constructors, one will take 1 parameter of type String name











    /**
     * Creates a product of the type ProductRecord.
     *
     * @return
     */
    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        ProductRecord newItem = new ProductRecord(id, name,price,category);
        listOfProducts.add(newItem);
        return newItem;
    }

    public static Warehouse getInstance(){
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean getProducts() {
        return false;
    }

    public boolean getProductById(Object uuid) {
        return false;
    }

    public void updateProductPrice(Object uuid, BigDecimal bigDecimal) {
    }

    public boolean getChangedProducts() {
        return false;
    }

    public boolean getProductsGroupedByCategories() {
        return false;
    }

    public boolean getProductsBy(Category meat) {
        return false;
    }
}
