package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(UUID.randomUUID(),"milk", Category.of("milk"), BigDecimal.valueOf(23));





    }
}
