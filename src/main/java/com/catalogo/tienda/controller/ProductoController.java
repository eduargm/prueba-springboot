/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catalogo.tienda.controller;

import com.catalogo.tienda.service.ProductoService;
import com.catalogo.tienda.modelo.Categoria;
import com.catalogo.tienda.modelo.Producto;
import com.catalogo.tienda.service.CategoriaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author USUARIO
 */
@Controller
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService categoriaService;
   
    @GetMapping
    public String findProductos(Model modelo) {
        
        List<Producto> productos = new ArrayList<>();
        productos = productoService.findAll();
        modelo.addAttribute("productos", productos);
        return "adminproduct";
    }
    
    @GetMapping("/signup")
    public String showFormProducto(Producto producto, Model model) {

        List<Categoria> categorias = new ArrayList<>();
        categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);

        return "add-product";
    }
    
    @PostMapping("/add")
    public String addProducto(@RequestParam(value = "mensaje", required = false,defaultValue = "")String mensaje, Producto producto, Model model, @RequestParam("file") MultipartFile file) {

        Producto pro = productoService.findById(producto.getIdproducto());
        String var;

        if (pro.getIdproducto() == 0) {
            String rutaFoto = productoService.uploadFile(file);
            producto.setFotouno(rutaFoto);
            productoService.save(producto);
            List<Producto> productos = new ArrayList<>();
            productos = productoService.findAll();
            model.addAttribute("productos", productos);
            mensaje = "Product added";
            model.addAttribute("mensaje", mensaje);
            var = "adminproduct";
        }
        
        else{
            List<Categoria> categorias = new ArrayList<>();
            categorias = categoriaService.findAll();
            model.addAttribute("categorias", categorias);
            mensaje = "Please enter another ID";
            model.addAttribute("mensaje", mensaje);
            var = "add-product";
        }

        return var;
    }
    
    @GetMapping("/edit/{idproducto}")
    public String showUpdateForm(@PathVariable("idproducto") Integer idproducto, Model model) {
        List<Categoria> categorias = new ArrayList<>();
        categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        Producto producto = productoService.findById(idproducto);
        model.addAttribute("producto", producto);
        return "upd-product";
    }

    @PostMapping("/update/{idproducto}")
    public String updateStudent(@PathVariable("idproducto") long idproducto, Producto producto, BindingResult result,
            Model model) {
        
        productoService.save(producto);
        List<Producto> productos = new ArrayList<>();
        productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "adminproduct";
    }
    
    @GetMapping("delete/{idproducto}")
    public String deleteProducto(@PathVariable("idproducto") Integer idproducto, Model model) {
        productoService.deleteProduct(idproducto);
        List<Producto> productos = new ArrayList<>();
        productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "adminproduct";
    }
   
    
}
