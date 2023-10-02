package org.example.warehouse;

public class Category {
    String categoryName;


    private Category() {
    }

    /**
     * will create a new object of type Category
     * */
    public static Category of(String categoryName){
        Category category = new Category();
        category.categoryName = categoryName;
        return category;
    }


    public String getName() {
        return this.categoryName;
    }
}
