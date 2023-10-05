package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {

    private List<ProductRecord> listOfProducts = new ArrayList<>();
    /**
     * list that contains all the products in the store
     */
    private List<ProductRecord> listOfChangedProducts = new ArrayList<>();
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
     */
    public static Warehouse getInstance() {
        if (uniqueInstance == null) {
            // If the instance is null, create a new instance with the default name
            uniqueInstance = new Warehouse();
        }
        return uniqueInstance;
    }

    /**
     * checks if the variable uniqueInstance is null OR if uniqueInstance has been given a name which equals the name in the parameter
     * otherwise it will return the existing one
     *
     * @param name becomes the name for the object
     */
    public static Warehouse getInstance(String name) {
        if (uniqueInstance == null || !uniqueInstance.name.equals(name)) {
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
        ProductRecord newItem = new ProductRecord(id, name, price, category);
        listOfProducts.add(newItem);
        return newItem;
    }


    public boolean isEmpty() { // todo look more at what to do with this method
        return listOfProducts.isEmpty();
    }

    /**
     * returns an immutable version of the listOfProducts
     */
    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(listOfProducts);
    }

    /**
     * todo read what optional does.. seems to work with the test
     * searches for a specified product, if the product is found we return it
     * otherwise return optional.empty (instead of null)
     */
    public Optional<ProductRecord> getProductById(UUID uuid) {
        for (ProductRecord product : listOfProducts) {
            if (product.uuid().equals(uuid)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    /***/
    public void updateProductPrice(Object uuid, BigDecimal bigDecimal) {


        // throws exception if the listOfProducts does not contain the specified uuid
        // this works because we have overwritten the equals method in ProductRecord to compare UUID instead of object reference
        boolean found = false;
        for (ProductRecord curProduct : listOfProducts){
            if (curProduct.productUUID.equals(uuid)) {
                found = true;
                break;
            }
        }

        if (!found){
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }else {
            for (ProductRecord curProduct : listOfProducts) {
                if (curProduct.productUUID.equals(uuid)) {
                    curProduct.setProductPrice(bigDecimal);
                    listOfChangedProducts.add(curProduct);
                }
            }
        }
    }

    public List<ProductRecord> getChangedProducts() {
        return listOfChangedProducts;
    }

    // todo should return a map using keys and values, maybe key is category and value is the item
    public boolean getProductsGroupedByCategories() {
        return false;
    }

    // todo should return all the product sorted by input category?
    public List<ProductRecord> getProductsBy(Category category) {

        // todo temporary code just to compile
        List<ProductRecord> productsInCategory = new ArrayList<>();
        for (ProductRecord product : listOfProducts) {
            if (product.category().equals(category)) {
                productsInCategory.add(product);
            }
        }
        return productsInCategory;
    }


}
