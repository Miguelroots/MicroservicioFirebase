/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.grupoc.Impl;

import com.example.grupoc.DTO.PostDTO;
import com.example.grupoc.firebase.FirebaseInizializer;
import com.example.grupoc.service.PostService;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USER
 */
@Service
public class PostServiceImpl implements PostService{
    
    @Autowired
    private FirebaseInizializer firebase;
    
    @Override
    public List<PostDTO> list() {
        List<PostDTO> response =  new ArrayList<>();
        PostDTO post;
        ApiFuture<QuerySnapshot> querysnap = getCollection().get();
        try {
            for(DocumentSnapshot doc: querysnap.get().getDocuments()){
                post = doc.toObject(PostDTO.class);
                post.setId(doc.getId());
                response.add(post);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean add(PostDTO post) {
        Map<String, Object> docData = getDocData(post);
        
        ApiFuture<WriteResult> writeResultApiFuture =  getCollection().document().create(docData);//guardando el documento dentro de la coleccion
        
        try {
            if (null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean edit(String id, PostDTO post) {
        Map<String, Object> docData = getDocData(post);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).set(docData);
        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delete(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
    
    private CollectionReference getCollection(){
        return firebase.getFirestore().collection("Empleado");
    }
    
    private Map<String, Object> getDocData(PostDTO post) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("nombre", post.getNombre());
        docData.put("cedula", post.getCedula());
        docData.put("cargo", post.getCargo());
        docData.put("direccion", post.getDireccion());
        docData.put("edad", post.getEdad());
        docData.put("email", post.getEmail());
        docData.put("sexo", post.getSexo());
        docData.put("telefono", post.getTelefono());
        return docData;
    }
    
    /*
        docData.put("Nombre", post.getNombre());
        docData.put("Cargo", post.getCargo());
        docData.put("Cedula", post.getCedula());
        docData.put("Direccion", post.getDireccion());
        docData.put("Edad", post.getEdad());
        docData.put("Email", post.getEmail());
        docData.put("Sexo", post.getSexo());
        docData.put("Telefono", post.getTelefono());
        */
        //CollectionReference posts = getCollection();
        
    
}
