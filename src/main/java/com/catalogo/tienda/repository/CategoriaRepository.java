package com.catalogo.tienda.repository;


import com.catalogo.tienda.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author USUARIO
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}
