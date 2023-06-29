package com.egg.biblioteca.controladores;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.entidades.Autor;


@Controller
@RequestMapping("/autor")
public class AutorControlador {

    private AutorServicio autorServicio;

    public AutorControlador(AutorServicio autServicio){
        this.autorServicio = autServicio;
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam( name = "nombre") String nombre){
        try {
            autorServicio.crearAutor(nombre);
        } catch (Exception e) {
            System.out.println("Error al intentar guardar al autor");
            // TODO: handle exception
            return "autor_form.html";
            
        }
        
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Autor> autores = autorServicio.listarAutores();
        modelo.addAttribute("autores", autores);
        return "autor_lista.html";
        
    }

}
