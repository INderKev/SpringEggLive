package com.egg.biblioteca.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.egg.biblioteca.entidades.*;
import com.egg.biblioteca.excepciones.MyException;
import com.egg.biblioteca.repository.AutorRepositorio;
import com.egg.biblioteca.repository.EditorialRepositorio;
import com.egg.biblioteca.repository.LibroRepositorio;

import jakarta.transaction.Transactional;

@Service
public class LibroServicio {

    private LibroRepositorio libroRepositorio;
    private AutorRepositorio autorRepositorio;
    private EditorialRepositorio editorialRepositorio;

    public LibroServicio(LibroRepositorio libRepo, AutorRepositorio AuRepo, EditorialRepositorio EdRepo){
        this.libroRepositorio = libRepo;
        this.autorRepositorio = AuRepo;
        this.editorialRepositorio = EdRepo;
    }


    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MyException {
        validar(isbn, titulo , idAutor, idEditorial, ejemplares );
		Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libro libro = new Libro();
        libro.setId(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        libro.setEditorial(editorial);
        libro.setAutor(autor);


        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros (){
        List<Libro> libros = new ArrayList();
        libros = libroRepositorio.findAll();
        return libros;
    }

    public void modificarLibro (Long isbn, String titulo, String nomAutor, String nomEditorial, Integer ejemplares) throws MyException {
		validar(isbn, titulo, nomAutor, nomEditorial, ejemplares);
		Optional<Libro> respuestaLibro = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(nomAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(nomEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
        }
        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }

        if (respuestaLibro.isPresent()) {
            Libro libro = respuestaLibro.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            libroRepositorio.save(libro);
        }		
        
    }

    private void validar (Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MyException{
        if(isbn==null){
            throw new MyException("El isbn no puede ser nulo");
        }
        if(titulo.isEmpty()|| titulo == null){
            throw new MyException("El titulo no puede ser nulo o estar vacío");
        }
        if(idAutor.isEmpty() || idAutor == null){
            throw new MyException("El nombre del autor no puede ser nulo o vacío");
        }
        if(idEditorial.isEmpty() || idEditorial == null){
            throw new MyException("El nombre de la editorial no puede ser nulo o vacío");
        }
        if(ejemplares == null || ejemplares < 0 ){
            throw new MyException("Los ejemplares no pueden ser negativos");
        }
	}
    
}
