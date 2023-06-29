package com.egg.biblioteca.entidades;

import org.hibernate.annotations.GenericGenerator;

import com.egg.biblioteca.enumeraciones.Rol;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue( generator = "uuid")
    @GenericGenerator( name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario(){

    }

    public String getId(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public Rol getRol(){
        return this.rol;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setEmail( String email){
        this.email=email;
    }

    public void setPassword( String password){
        this.password = password;
    }

    public void setRol( Rol rol){
        this.rol = rol;
    }
}
