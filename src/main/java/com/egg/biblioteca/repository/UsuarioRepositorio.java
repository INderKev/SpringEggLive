package com.egg.biblioteca.repository;


import com.egg.biblioteca.entidades.Usuario;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    /* 
    @Query("SELECT u FROM usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    */
}
