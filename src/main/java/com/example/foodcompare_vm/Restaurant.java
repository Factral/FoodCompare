package com.example.foodcompare_vm;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String id;
    private String name;
    private String phonenumber;
    private String adress;
    ArrayList<Item> items=new ArrayList<Item>();
    ArrayList<Platform> platforms=new ArrayList<Platform>();
    private String srcImage;

    public Restaurant(String id, String name, String phonenumber, String adress, String srcImage){
        this.id = id;
        this.name = name;
        this.phonenumber=phonenumber;
        this.adress=adress;
        this.srcImage = srcImage;
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

    public String getSrcImage() {
        return srcImage;
    }






}
