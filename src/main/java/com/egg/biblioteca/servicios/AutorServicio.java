package com.egg.biblioteca.servicios;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MyException;
import com.egg.biblioteca.repository.AutorRepositorio;

import jakarta.transaction.Transactional;

@Service
public class AutorServicio {

    AutorRepositorio autorRepositorio;

    public AutorServicio(AutorRepositorio autorRepo){
        this.autorRepositorio = autorRepo;
    }

    //el id es generado de manera automatica
    @Transactional
    public void crearAutor(String nombre) throws MyException{
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        System.out.println("Este es el nombre del autor que ya se va a guardar: "+ nombre);
        autorRepositorio.save(autor);
    }

    @Transactional
    public void modificarAutor(String nombre, String id) throws MyException{
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = new Autor();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }
    }

    private void validar (String nombre) throws MyException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MyException("El nombre no puede estar nulo o vacío");
        }
    }
}
