/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catalogo.tienda.service;

import com.catalogo.tienda.modelo.Usuario;
import com.catalogo.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class UsuarioService {
    
    @Autowired
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void save(Usuario usuario){
       repository.save(usuario);
    }  
    
    public Integer findUsuario(Usuario usuario) {

        Integer control = 0;

        try {

            Usuario pro = repository.findByUsername(usuario.getUsername());
            control = pro.getIdusuario();

        } catch (Exception e) {
            control = 0;
        }

        return control;

    }
    
    public String validarUsuario(Usuario usuario) {

        Integer control = 0;
        String var = null;

        try{
        
        Usuario pro = repository.findByUsername(usuario.getUsername());
        control = pro.getIdusuario();

        if (control == 0) {
            var = "acces deneged";
        } else {
            if (pro.getUsername().equals(usuario.getUsername()) && pro.getPassword().equals(usuario.getPassword())) {
                var = "ok";
            } else {
                var = "login incorrect";
            }
        }
        
        }catch(Exception e){
            var = "acces deneged";
        }
        
        return var;
    }
     
    
}
