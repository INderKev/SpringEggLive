package com.egg.biblioteca.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.egg.biblioteca.entidades.Editorial;


@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    
}
