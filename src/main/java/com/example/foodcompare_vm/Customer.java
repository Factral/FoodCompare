package com.example.foodcompare_vm;

/**
 * Clase que representa a un cliente.
 * Esta clase contiene información básica de un cliente, como su nombre, dirección, email y número de teléfono.
 *
 * @author Fabian Perez
 * @version 1.0
 */
public class Customer {
    private String name;
    private String address;
    private String email;
    private Integer phone;

    /**
     * Constructor de la clase Customer.
     *
     * @param name    El nombre del cliente.
     * @param address La dirección del cliente.
     * @param email   El email del cliente.
     * @param phone   El número de teléfono del cliente.
     */
    public Customer(String name, String address, String email, Integer phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getName(){
        return name;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return El número de teléfono del cliente.
     */
    public Integer getPhone(){
        return phone;
    }
}
