package org.example.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private String categoryName;
    private static final List<Category> existingCategories = new ArrayList<>();

    private Category() {
    }

    /**
     * will create a new object of type Category
     */
    public static Category of(String categoryName) {

        if (categoryName == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }

        String capitalCategoryName = capitalizeFirstLetter(categoryName);

        // todo add streams instead of loop
        for (Category currentCategory : existingCategories) {
            if (currentCategory.getName().equals(capitalCategoryName)) {
                return currentCategory;
            }
        }

        return CreateNewCategory(capitalCategoryName);
    }

    private static Category CreateNewCategory(String capitalCategoryName) {
        Category category = new Category();
        category.categoryName = capitalCategoryName;
        existingCategories.add(category);
        return category;
    }

    private static String capitalizeFirstLetter(String categoryName) {

        return categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1);
    }

    public String getName() {
        return this.categoryName;
    }


    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }
}
