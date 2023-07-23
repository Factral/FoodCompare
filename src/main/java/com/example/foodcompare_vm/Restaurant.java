package com.example.foodcompare_vm;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String id;
    private String name;
    private int phonenumber;
    private String adress;
    ArrayList<Item> items=new ArrayList<Item>();
    ArrayList<Platform> platforms=new ArrayList<Platform>();

    public Restaurant(String id, String name, Integer phonenumber, String adress){
        this.id = id;
        this.name = name;
        this.phonenumber=phonenumber;
        this.adress=adress;
    }

    public void additem(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    //get name
    public String getName() {
        return name;
    }

    public void addPlatforms(List<Platform> platforms) {
        for (Platform platform : platforms) {
            platform.addRestaurant(this);
            this.platforms.add(platform);
        }
    }




}
