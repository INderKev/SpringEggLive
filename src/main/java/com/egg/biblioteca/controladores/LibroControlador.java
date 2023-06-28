package com.egg.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.excepciones.MyException;
import com.egg.biblioteca.servicios.LibroServicio;

@Controller
@RequestMapping("libro")
public class LibroControlador {
    private LibroServicio libroServicio;

    public LibroControlador (LibroServicio libServicio){
        this.libroServicio = libServicio;
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro (
    @RequestParam (name = "isbn", required = false ) Long isbn, 
    @RequestParam (name = "titulo") String titulo, 
    @RequestParam (name = "ejemplares", required = false) Integer ejempleres,
    @RequestParam (name = "idAutor") String idAutor, 
    @RequestParam (name = "idEditorial") String idEditorial,
    ModelMap modelo){
        try {
            libroServicio.crearLibro(isbn, titulo, ejempleres, idAutor, idEditorial);
            // si todo pasa bien
            modelo.put("exito", "El libro fue cargado correctamente");
        } catch (MyException e) {
            System.out.println("El isbn es: --------------------------------------3 "+isbn);
            modelo.put("error", e.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }
}
