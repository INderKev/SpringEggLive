package com.egg.biblioteca.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Editorial {

    @Id
    @GeneratedValue( generator = "uuid")
    @GenericGenerator( name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;

    public Editorial (){

    }

    public String getId (){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }
}
