/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.grupoc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.grupoc.DTO.PostDTO;
import com.example.grupoc.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author USER
 */
@RestController
@RequestMapping(value = "/grupoc")
public class PostController {
    
    //Inyectar el servicio implements
    @Autowired
    private PostService service;
    
    @GetMapping(value = "/greet/{name}")
    public String greet(@PathVariable(value = "name") String name){
        return "Hola " + name ;
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity list(){
        return new ResponseEntity(service.list(), HttpStatus.OK);
    }
    //no codificar logica de negocio dentro del controlador
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody PostDTO post){
        return new ResponseEntity(service.add(post),HttpStatus.OK);
    }
    
    @PutMapping(value = "/{id}/update")
    public ResponseEntity edit(@PathVariable(value = "id")String id, @RequestBody PostDTO post){
        return new ResponseEntity(service.edit(id, post),HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity delete(@PathVariable(value = "id")String id){
        return new ResponseEntity(service.delete(id),HttpStatus.OK);
    }
}
