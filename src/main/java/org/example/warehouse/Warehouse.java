package org.example.warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class Warehouse {
    /**list that contains all the products in the store*/
    ArrayList<ProductRecord> listOfProducts = new ArrayList<>();


    /**
     * Creates a product of the type ProductRecord.
     * */
    public void addProduct(UUID id, String name, Object category, BigDecimal price) {
    }


}
