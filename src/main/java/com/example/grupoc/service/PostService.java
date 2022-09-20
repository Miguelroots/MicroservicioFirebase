/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.grupoc.service;

import com.example.grupoc.DTO.PostDTO;
import java.util.List;

/**
 *
 * @author USER
 */
public interface PostService {
    
    List<PostDTO> list();
    
    Boolean add(PostDTO post);
    
    Boolean edit(String id, PostDTO post);
    
    Boolean delete(String id);
}
