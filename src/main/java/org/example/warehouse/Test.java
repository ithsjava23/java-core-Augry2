package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        // UUID.randomUUID()
        UUID testID = new UUID(123L, 0);


        Warehouse warehouse = Warehouse.getInstance("myStore");
        warehouse.addProduct(testID,"milk", Category.of("milk"), BigDecimal.valueOf(23));

        System.out.println(warehouse.getProducts());

        warehouse.updateProductPrice(testID, BigDecimal.valueOf(555,2));

        System.out.println(warehouse.getProducts());


    }
}
