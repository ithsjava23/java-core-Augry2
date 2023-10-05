package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {

    private List<ProductRecord> listOfProducts = new ArrayList<>();
    private List<ProductRecord> listOfChangedProducts = new ArrayList<>();
    private String name;

    private Warehouse(String name) {
        this.name = name;
        this.listOfProducts = new ArrayList<>();
        this.listOfChangedProducts = new ArrayList<>();
    }

    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }
    public static Warehouse getInstance(){
        return new Warehouse("New Warehouse");
    }







//    private List<ProductRecord> listOfProducts = new ArrayList<>();
//    private List<ProductRecord> listOfChangedProducts = new ArrayList<>();
//    private String name;
//    private static Warehouse uniqueInstance; // todo read more about this thing, singleton pattern
//
//    private Warehouse() {
//        this.name = "basic warehouse";
//    }
//
//    private Warehouse(String name) {
//        this.name = name;
//    }
//
//    /**
//     * if a warehouse of the same name does not already exist, it creates a
//     * new warehouse and sets the name to default warehouse
//     */
//    public static Warehouse getInstance() {
//        if (uniqueInstance == null) {
//            uniqueInstance = new Warehouse();
//            uniqueInstance.clearLists();
//        }
//        return uniqueInstance;
//    }
//
//    /**
//     * checks if the variable uniqueInstance is null OR if uniqueInstance has been given a name which equals the name in the parameter
//     * otherwise it will return the existing one
//     *
//     * @param name becomes the name for the object
//     */
//    public static Warehouse getInstance(String name) {
//        if (uniqueInstance == null || !uniqueInstance.name.equals(name)) {
//            uniqueInstance = new Warehouse(name);
//            uniqueInstance.clearLists();
//        }
//        return uniqueInstance;
//    }
//
//    private void clearLists() {
//        listOfProducts.clear();
//        listOfChangedProducts.clear();
//    }

    /**
     * Creates an object of the type ProductRecord. and adds it to the list
     *
     * @return returns the created product
     */
    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        // Check if a product with the same UUID already exists
        for (ProductRecord existingProduct : listOfProducts) {
            if (existingProduct.uuid().equals(id)) {
                // If a product with the same UUID exists, return the existing product and do not create a new one
                return existingProduct;
            }
        }

        // If no product with the same UUID exists, create a new one and add it to the list
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
        return Collections.unmodifiableList(this.listOfProducts);

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

    /**
     * Updates the price of a specified item. throws exception if item cannot be found
     */
    public void updateProductPrice(Object uuid, BigDecimal bigDecimal) {

        Optional<ProductRecord> productToUpdate = getProductById((UUID) uuid);
        if (productToUpdate.isPresent()) {
            ProductRecord curProduct = productToUpdate.get();
            curProduct.setPrice(bigDecimal);
            listOfChangedProducts.add(curProduct);
        } else {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }


//        boolean found = false;
//        for (ProductRecord curProduct : listOfProducts){
//            if (curProduct.uuid().equals(uuid)) {
//                found = true;
//                break;
//            }
//        }
//
//        if (!found){
//            throw new IllegalArgumentException("Product with that id doesn't exist.");
//        }else { // todo replace with streams maybe
//            for (ProductRecord curProduct : listOfProducts) {
//                if (curProduct.uuid().equals(uuid)) {
//                    curProduct.setProductPrice(bigDecimal);
//                    listOfChangedProducts.add(curProduct);
//                }
//            }
//        }
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

        List<ProductRecord> productsInCategory = new ArrayList<>();
        for (ProductRecord product : listOfProducts) {
            if (product.category().equals(category)) {
                productsInCategory.add(product);
            }
        }
        return productsInCategory;
    }


}
