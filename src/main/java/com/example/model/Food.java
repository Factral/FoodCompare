package com.example.model;

public class Food {
    private String name;
    private String imageSrc; // Corregido: debe comenzar con minúscula
    private String restaurant;
    private String price;
    private String desc;
    private String platform;
    private Integer id;

    private String restauranName;


    public Integer getId() {
    	return id;
    }

    public void setId(Integer id) {
    	this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDesc(String desc) {
    	this.desc = desc;
    }

    public String getDesc() {
    	return desc;
    }

    public void setPlatform(String platform) {
    	this.platform = platform;
    }

    public String getPlatform() {
    	return platform;
    }

    public void setRestaurantName(String restauranName) {
    	this.restauranName = restauranName;
    }

    public String getRestauranName() {
    	return restauranName;
    }
}
