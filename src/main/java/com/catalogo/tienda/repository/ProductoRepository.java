/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catalogo.tienda.repository;

import com.catalogo.tienda.modelo.Producto;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author USUARIO
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {}

