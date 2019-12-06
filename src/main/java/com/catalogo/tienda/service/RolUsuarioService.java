/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catalogo.tienda.service;

import com.catalogo.tienda.modelo.Rol;
import com.catalogo.tienda.modelo.Rolusuario;
import com.catalogo.tienda.repository.RolUsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class RolUsuarioService {
    
    @Autowired
    private final RolUsuarioRepository repository;

    public RolUsuarioService(RolUsuarioRepository repository) {
        this.repository = repository;
    }
    
    public void save(Rolusuario rolusuario) {

        Rol r = new Rol(2);
        rolusuario.setIdrol(r);
        repository.save(rolusuario);
    }   
    
    public String findUser(Integer idusu) {

        Integer control = 0;
        String var = null;
        Optional<Rolusuario> pro = repository.findById(idusu);
        Rol ro = new Rol();
        ro = pro.get().getIdrol();
        control = ro.getIdrol();

        if (control != 0) {
            if (control == 1) {
                var = "admin";
            } else {
                var = "user";
            }
        }

        return var;

    }
    
}
