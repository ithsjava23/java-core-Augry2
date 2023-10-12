package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {

    private final List<ProductRecord> listOfProducts;
    private final List<ProductRecord> listOfChangedProducts;
    private final String name; // used by tests so it's ok that its grayed-out

    private Warehouse(String name) {
        this.name = name;
        this.listOfProducts = new ArrayList<>();
        this.listOfChangedProducts = new ArrayList<>();
    }

    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }

    public static Warehouse getInstance() {
        return new Warehouse("New Warehouse");
    }

    /**
     * Creates an object of the type ProductRecord. and adds it to the list
     *
     * @return returns the created product
     */
    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        UUID randomId = UUID.randomUUID();
        // if the name is empty or if category is null, throw exception
        checkForExceptions(name, category);

        for (ProductRecord currentProduct : listOfProducts) {
            if (currentProduct.uuid().equals(id)) {
                throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
            }
        }

        // if price is null, set it to 0
        if (price == null)
            price = BigDecimal.valueOf(0);

        // if id is null, give it a random UUID
        if (id == null)
            id = randomId;


        // Check if a product with the same UUID already exists
        for (ProductRecord existingProduct : listOfProducts) {
            if (existingProduct.uuid().equals(id))
                return existingProduct; // If a product with the same UUID exists, return the existing product and do not create a new one
        }

        // If no product with the same UUID exists, create a new one and add it to the list
        ProductRecord newItem = new ProductRecord(id, name, price, category);
        listOfProducts.add(newItem);
        return newItem;
    }

    private static void checkForExceptions(String name, Category category) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        } else if (category == null) { // have to use == otherwise it will cast a nullPointerException
            throw new IllegalArgumentException("Category can't be null.");
        }
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
     * searches for a specified product, if the product is found we return it
     * otherwise return empty optional
     */
    public Optional<ProductRecord> getProductById(UUID uuid) {
        return listOfProducts.stream()
                .filter(product -> product.uuid().equals(uuid))
                .findFirst(); // returns first element that matches, or empty optional if no match is found
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
    }

    public List<ProductRecord> getChangedProducts() {
        return listOfChangedProducts;
    }


    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        HashMap<Category, List<ProductRecord>> mapOfProducts = new HashMap<>();
        HashSet<Category> categoryHashSet = new HashSet<>();
        createHashSetOfCategories(categoryHashSet);

        for (Category currentCategory : categoryHashSet) {
            mapOfProducts.put(currentCategory, getProductsBy(currentCategory));
        }

        System.out.println(mapOfProducts);

        return mapOfProducts;
    }

    private void createHashSetOfCategories(HashSet<Category> categoryHashSet) {
        // creates a stream, .map creates a new stream of categories, by calling on the category method in the productRecord class, then adds it to hashset
        listOfProducts.stream()
                .map(ProductRecord::category)
                .forEach(categoryHashSet::add);
    }

    /**
     * returns a list containing all items of a specified category
     */
    public List<ProductRecord> getProductsBy(Category category) {

        return listOfProducts.stream() // create stream of ProductRecords
                .filter(product -> product.category().equals(category)) // if the category of the product in the list = the category sent in as parameter
                .collect(Collectors.toList()); // add to a new list, send it up and return it
    }


}
