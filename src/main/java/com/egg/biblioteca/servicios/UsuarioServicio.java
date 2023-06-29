package com.egg.biblioteca.servicios;
/* 
import java.util.ArrayList;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
*/
import org.springframework.stereotype.Service;

import com.egg.biblioteca.excepciones.MyException;
import com.egg.biblioteca.repository.UsuarioRepositorio;

import com.egg.biblioteca.entidades.Usuario;
import com.egg.biblioteca.enumeraciones.Rol;

import jakarta.transaction.Transactional;


//public class UsuarioServicio implements UserDetailsService{
@Service
public class UsuarioServicio {

    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio(UsuarioRepositorio usuRepo){
        this.usuarioRepositorio = usuRepo;
    }

    @Transactional
    public void registrar(String nombre, String email, String password) throws MyException{
        validar(nombre, email, password);
        Usuario usuario  = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        //usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setPassword(password);
        usuario.setRol(Rol.USER);
        usuarioRepositorio.save(usuario);
    }

    private void validar (String nombre, String email, String password)throws MyException{
        if (nombre.isEmpty() || nombre == null) {
            throw new MyException("El nombre esta vacío o nulo");
        }
        if (email.isEmpty() || email == null) {
            throw new MyException("El email no puede estar vacío o nulo");
        }
        if (password.isEmpty() || password == null || password.length()<8) {
            throw new MyException("La contraseña esta vacía es nula o tiene menos de 8 caracteres");
        }
    }

    //el email es con lo que se autentica a cada uno de los usuario
    /* 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        if (usuario != null) {
            //permisos otorgados
            List <GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString()); // ROLE_USER

            permisos.add(p);

            //User(nombre, contraseña, colección de permisos)
            return  new User(usuario.getEmail(), usuario.getPassword(), permisos );
        }else{
            return null;
        }
    }
    */

}
