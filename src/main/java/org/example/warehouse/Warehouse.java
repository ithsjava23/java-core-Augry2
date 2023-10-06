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
    public Map getProductsGroupedByCategories() {
        HashMap<Category, List<ProductRecord>> mapOfProducts = new HashMap<Category, List<ProductRecord>>();
        HashSet<Category> categoryHashSet = new HashSet<>();
        createHashSetOfCategories(categoryHashSet);

        for (Category currentCategory : categoryHashSet) {
            mapOfProducts.put(currentCategory, getProductsBy(currentCategory));
        }

        System.out.println(mapOfProducts);

        return mapOfProducts;
    }

    private void createHashSetOfCategories(HashSet<Category> categoryHashSet) {
        for (ProductRecord currentCategory : listOfProducts) {
            // create a list of categories which contains no doublettes
            categoryHashSet.add(currentCategory.category());
        }
    }

    /**
     * returns a list containing all items of a specified category
     */
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
