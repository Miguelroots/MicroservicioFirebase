/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.grupoc.DTO;

import lombok.Data;

/**
 *
 * @author USER
 */
@Data
public class PostDTO {
    private String id;
    private String nombre;
    private String cedula;
    private String direccion;
    private String telefono;
    private String sexo;
    private String email;
    private String edad;
    private String cargo;
}
