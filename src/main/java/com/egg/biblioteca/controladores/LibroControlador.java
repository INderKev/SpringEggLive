package com.egg.biblioteca.controladores;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MyException;
import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.servicios.EditorialServicio;
import com.egg.biblioteca.servicios.LibroServicio;

@Controller
@RequestMapping("libro")
public class LibroControlador {
    private LibroServicio libroServicio;
    private AutorServicio autorServicio;
    private EditorialServicio editorialServicio;

    public LibroControlador (LibroServicio libServicio, AutorServicio autServicio, EditorialServicio ediServicio){
        this.libroServicio = libServicio;
        this.autorServicio = autServicio;
        this.editorialServicio = ediServicio;
    }

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo){
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
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
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            modelo.put("error", e.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }
}
