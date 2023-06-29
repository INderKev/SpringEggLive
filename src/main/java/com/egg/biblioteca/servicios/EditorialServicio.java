package com.egg.biblioteca.servicios;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;


import org.springframework.stereotype.Service;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MyException;
import com.egg.biblioteca.repository.EditorialRepositorio;

import jakarta.transaction.Transactional;

@Service
public class EditorialServicio {
    EditorialRepositorio editorialRepositorio;

    public EditorialServicio(EditorialRepositorio ediRepo){
        this.editorialRepositorio = ediRepo;
    }

    //transactional: si ocurre alguna excepción se vuelve atrás en la transacción 
    @Transactional
    public void crearEditorial(String nombreEditorial)throws MyException {
        validar(nombreEditorial, nombreEditorial);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombreEditorial);
        this.editorialRepositorio.save(editorial);
    }

    public List<Editorial> listarEditoriales (){
        List<Editorial> editoriales = new ArrayList();
        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }

    @Transactional
    public void modificarEditorial(String id , String nombreEditorial) throws MyException {
        validar(id, nombreEditorial);
        Optional<Editorial> respuesta  = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombreEditorial);
            editorialRepositorio.save(editorial);
        }
    }

    private void validar( String id, String nombreEditorial) throws MyException {
        if (id.isEmpty() || id == null) {
            throw new MyException("El id de la editorial no puede estar vacío o nulo");

        }
        if (nombreEditorial.isEmpty() || nombreEditorial == null){
            throw new MyException("El nombre de la editorial no puede estar vacío o nulo");
        }
    }
}
