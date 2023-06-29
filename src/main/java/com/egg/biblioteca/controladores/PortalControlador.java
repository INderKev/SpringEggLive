package com.egg.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.servicios.UsuarioServicio;


@Controller
@RequestMapping
public class PortalControlador {

	

	private UsuarioServicio usuarioServicio;

	public PortalControlador(UsuarioServicio usuServicio){
		this.usuarioServicio = usuServicio;
	}
	
	
	@GetMapping("/")
	public String index(){
		return "index.html";
	}

	

	@GetMapping("/registrar")
	public String registrar(){
		return "registro.html";
	}

	@PostMapping("/registro")
	public String registro(
		@RequestParam(name = "nombre") String nombre,
		@RequestParam(name = "email") String email,
		@RequestParam(name = "password") String password,
		ModelMap modelo
	){
		try {
			usuarioServicio.registrar(nombre, email, password);
			modelo.put("exito", "Usuario registrado satisfactoriamente!");
			return "index.html";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			modelo.put("nombre", nombre);
			modelo.put("email", email);
			modelo.put("password", password);
			return "registro.html";
		}
		
	}
	 
	@GetMapping("/login")
	public String login(
		@RequestParam(required = false) String error,
		ModelMap modelo
	){
		if (error!=null) {
			modelo.put("error","usuario o contraseña invalida");
		}
		return "login.html";
	}

	@GetMapping("/inicio")
	public String inicio(){
		return "inicio.html";
	}

}