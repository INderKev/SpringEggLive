package com.egg.biblioteca.entidades;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Libro {
    @Id
    private Long isbn;
    private String titulo;
    private Integer ejemplares;

    @Temporal(TemporalType.DATE)
    private Date alta;
    
    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Editorial editorial;

    public Libro (){

    }

    public Long getId(){
        return this.isbn;
    }

    public void setId(Long isbn){
        this.isbn = isbn;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public Integer getEjemplares(){
        return this.ejemplares;
    }

    public void setEjemplares(Integer ejemplares){
        this.ejemplares = ejemplares;
    }

    public void setAlta(Date date){
        this.alta = date;
    }

    public Date getAlta(){
        return this.alta;
    }

    public void setAutor(Autor autor){
        this.autor = autor;
    }

    public Autor getAutor (){
        return this.autor;
    }

    public void setEditorial( Editorial editorial){
        this.editorial = editorial;
    }

    public Editorial getEditorial( ){
        return this.editorial;
    }
}
