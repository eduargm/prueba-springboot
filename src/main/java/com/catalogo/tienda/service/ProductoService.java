/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catalogo.tienda.service;

import com.catalogo.tienda.modelo.Producto;
import com.catalogo.tienda.repository.ProductoRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author USUARIO
 */
@Service
public class ProductoService {

    @Autowired
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository productRespository) {
        this.repository = productRespository;
    }

    public List<Producto> findAll() {
        return repository.findAll();
    }
    
    public void save(Producto producto){
       repository.save(producto);
    }  
    
    public Producto findById(Integer id) {

        Producto producto = new Producto();
        try {

            Optional<Producto> pro = repository.findById(id);
            producto.setIdproducto(pro.get().getIdproducto());
            producto.setNombre(pro.get().getNombre());
            producto.setDescripcion(pro.get().getDescripcion());
            producto.setPrecio(pro.get().getPrecio());
            producto.setPeso(pro.get().getPeso());
            producto.setIdcategoria(pro.get().getIdcategoria());
            producto.setFotouno(pro.get().getFotouno());

        } catch (Exception e) {
            producto.setIdproducto(0);
        }

        return producto;
    }
    
    public void deleteProduct(Integer id) {

        repository.deleteById(id);

    }
            
    @Value("${file.dir}")
    public String uploadDir;

    public String uploadFile(MultipartFile file) {

        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error uploadFile"+e);
            
        }
        
        return file.getOriginalFilename();
    }
    

}
