package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {

        // UUID.randomUUID()
        UUID testID1 = new UUID(1L, 0);
        UUID testID2 = new UUID(2L, 0);
        UUID testID3 = new UUID(3L, 0);
        //UUID testID = UUID.randomUUID();

        Warehouse warehouse = Warehouse.getInstance("myStore");

        warehouse.addProduct(testID1,"milk", Category.of("milk"), BigDecimal.valueOf(23));
        warehouse.addProduct(testID2,"banana", Category.of("fruit"), BigDecimal.valueOf(2));
        warehouse.addProduct(testID3,"apple", Category.of("fruit"), BigDecimal.valueOf(8));

        warehouse.getProductsGroupedByCategories();

    }
}
