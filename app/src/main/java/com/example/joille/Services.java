package com.example.joille;

public class Services {

    int id;
    String name;
    String description ;
    int value;
    int categories_id;
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Services(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Services(int id, String name, String description, int value, int categories_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.categories_id = categories_id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }
}
