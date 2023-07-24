package com.example.foodcompare_vm;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase que representa un carrito de compras.
 * Permite agregar y eliminar elementos, así como obtener la lista de elementos y el costo total.
 *
 * @author Fabián Pérez
 * @version 1.0
 */
public class Cart {

    private ArrayList<Map> listItems = new ArrayList<>();
    private int cartId;
    private double totalCost;

    /**
     * Agrega un elemento al carrito.
     *
     * @param itemInfo la información del elemento a agregar.
     */
    public void add(Map itemInfo) {
        listItems.add(itemInfo);
    }

    /**
     * Obtiene la lista de elementos en el carrito.
     *
     * @return la lista de elementos en el carrito.
     */
    public ArrayList<Map> getItems() {
        return listItems;
    }

    /**
     * Elimina todos los elementos del carrito.
     */
    public void clearCart() {
        listItems.clear();
    }

}
