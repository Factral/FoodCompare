package com.example.foodcompare_vm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Clase que representa un elemento.
 * Cada clase y cada método no-trivial debe estar documentado.
 * Toda la documentación utiliza TAGs del estándar de Javadoc.
 *
 * @author Fabian Perez
 * @version 1.0
 */
public class Item {

    private String name;

    Map<Platform, Integer> map = new HashMap<Platform, Integer>();

    private boolean availability;

    private Restaurant re;

    private String srcImage;
    private String description;

    private Integer id;

    /**
     * Constructor de la clase Item.
     *
     * @param name         el nombre del elemento
     * @param availability la disponibilidad del elemento
     * @param platforms    la lista de plataformas
     * @param prices       la lista de precios
     * @param re           el restaurante al que pertenece el elemento
     */
    public Item(Integer id,String name, Boolean availability, List<Platform> platforms, List<Integer> prices, Restaurant re, String srcImage, String description) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.re = re;
        this.srcImage = srcImage;
        this.description = description;

        for (int i = 0; i < platforms.size(); i++) {
            map.put(platforms.get(i), prices.get(i));
        }

        re.additem(this);
    }

    /**
     * Obtiene el nombre del elemento.
     *
     * @return el nombre del elemento
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el mapa de plataformas y precios del elemento.
     *
     * @return el mapa de plataformas y precios
     */
    public Map<Platform, Integer> getMap() {
        return map;
    }

    public List<Integer> getPrices() {
        List<Integer> prices = new ArrayList<Integer>();
        for (Map.Entry<Platform, Integer> entry : map.entrySet()) {
            prices.add(entry.getValue());
        }
        return prices;
    }

    public List<Platform> getPlatforms() {
        List<Platform> platforms = new ArrayList<Platform>();
        for (Map.Entry<Platform, Integer> entry : map.entrySet()) {
            platforms.add(entry.getKey());
        }
        return platforms;
    }

    //GETRESTAURANT
    public Restaurant getRestaurant() {
        return re;
    }
    //getsrc
    public String getImageSrc() {
        return srcImage;
    }

    public String getDesc() {
        return description;
    }

    public Integer getId() {
        return id;
    }

}
