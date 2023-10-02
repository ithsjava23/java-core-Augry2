package org.example.warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Warehouse {

    List<ProductRecord> listOfProducts = new ArrayList<>(); /**list that contains all the products in the store*/
    private String name;
    private static Warehouse uniqueInstance; // todo read more about this thing

    private Warehouse() {
        this.name = "basic warehouse";
    }

    private Warehouse(String name) {
        this.name = name;
    }

    /**
     * if a warehouse of the same name does not already exist, it creates a
     * new warehouse and sets the name to default warehouse
     * */
    public static Warehouse getInstance(){
        if (uniqueInstance == null) {
            // If the instance is null, create a new instance with the default name
            uniqueInstance = new Warehouse();
        }
        return uniqueInstance;
    }
    /**
     * checks if the variable instance is null, if it is null then it will create a new
     * warehouse object, otherwise it will return the existing one
     * @param name becomes the name for the object
     * */
    public static Warehouse getInstance(String name){
        if (uniqueInstance == null){
            uniqueInstance = new Warehouse(name);
        }
        return uniqueInstance;
    }


    /**
     * Creates an object of the type ProductRecord. and adds it to the list
     *
     * @return returns the created product
     */
    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        ProductRecord newItem = new ProductRecord(id, name,price,category);
        listOfProducts.add(newItem);
        return newItem;
    }



    public boolean isEmpty() {
        return false;
    }

    //todo should return a list or something
    public List getProducts() {
        return null;
    }

    // todo should return a product that matches the input uuid
    public ProductRecord getProductById(Object uuid) {
        return null;
    }

    public void updateProductPrice(Object uuid, BigDecimal bigDecimal) {
    }

    // todo should return the new changed product?
    public boolean getChangedProducts() {
        return false;
    }

    // todo should return a map using keys and values, maybe key is category and value is the item
    public boolean getProductsGroupedByCategories() {
        return false;
    }

    // todo should return all the product sorted by input category?
    public boolean getProductsBy(Category meat) {
        return false;
    }




}
