package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        long testIDValue = 123L; // Assuming 123 is your long value
        UUID testID = new UUID(testIDValue, 0);
        // UUID.randomUUID()

        Warehouse warehouse = Warehouse.getInstance("myStore");


        warehouse.addProduct(testID,"milk", Category.of("milk"), BigDecimal.valueOf(23));

        System.out.println(warehouse.getProductById(testID));




    }
}
