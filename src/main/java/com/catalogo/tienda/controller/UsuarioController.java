/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catalogo.tienda.controller;

import com.catalogo.tienda.modelo.Producto;
import com.catalogo.tienda.modelo.Rolusuario;
import com.catalogo.tienda.modelo.Usuario;
import com.catalogo.tienda.service.ProductoService;
import com.catalogo.tienda.service.RolUsuarioService;
import com.catalogo.tienda.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author USUARIO
 */
@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolUsuarioService rolUsuarioService;
    
    @Autowired
    private ProductoService productoService;

   
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index";
    }
    
    @PostMapping("/loginusu")
    public String logUser(@RequestParam(value = "mensaje", required = false,defaultValue = "")String mensaje, Usuario usuario, Model model, HttpSession session) {

        String access = null;
        
        String var = usuarioService.validarUsuario(usuario);
        
        if(var.equals("ok")){
            
            Integer idUsu = usuarioService.findUsuario(usuario);
            
            String varrol = rolUsuarioService.findUser(idUsu);
            
            if(varrol.equals("admin")){
                List<Producto> productos = new ArrayList<>();
                productos = productoService.findAll();
                model.addAttribute("productos", productos);
                session.setAttribute("idusuario", "idUsu");
                access = "adminproduct";
            }
            else{
                List<Producto> productos = new ArrayList<>();
                productos = productoService.findAll();
                model.addAttribute("productos", productos);
                session.setAttribute("idusuario", "idUsu");
                access = "buyproduct";
            }
            
        }
        
        else{
            mensaje = "The user does not exist or the password is incorrect";
            model.addAttribute("mensaje", mensaje);
            access = "index";
        }
       
        return access;
    }
    
    @GetMapping("/registration")
    public String regUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registration";
    }
    
    @PostMapping("/add-user")
    public String addUser(@RequestParam(value = "mensaje", required = false,defaultValue = "")String mensaje, Usuario usuario, Model model) {

        Integer cont = usuarioService.findUsuario(usuario);
        String var = null;

        if (cont == 0) {

            usuarioService.save(usuario);
            Rolusuario ru = new Rolusuario();
            ru.setIdusuario(usuario);
            rolUsuarioService.save(ru);
            mensaje = "Registered user";
            model.addAttribute("mensaje", mensaje);
            var = "index";

        }
        
        else{
            mensaje = "Please enter another username";
            model.addAttribute("mensaje", mensaje);
            var = "registration";
        }

        return var;
    }
    
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index";
    }

    
}
