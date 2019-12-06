/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catalogo.tienda.repository;

import com.catalogo.tienda.modelo.Rolusuario;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author USUARIO
 */
public interface RolUsuarioRepository extends JpaRepository<Rolusuario, Integer> {
    
}
