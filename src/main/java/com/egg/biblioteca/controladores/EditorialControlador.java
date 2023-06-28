package com.egg.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.servicios.EditorialServicio;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {

    private EditorialServicio editorialServicio;

    public EditorialControlador (EditorialServicio ediServicio){
        this.editorialServicio = ediServicio;
    }

     @GetMapping("/registrar")
    public String registrar(){
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam( name = "nombre") String nombre){
        try {
            editorialServicio.crearEditorial(nombre);
        } catch (Exception e) {
            System.out.println("Error al intentar guardar la editorial");
            // TODO: handle exception
            return "editorial_form.html";
            
        }
        
        return "index.html";
    }
    
}
