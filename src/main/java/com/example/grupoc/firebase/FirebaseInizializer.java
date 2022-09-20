/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.grupoc.firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.IOException;
import java.io.InputStream;
/**
 *
 * @author USER
 */

@Service
public class FirebaseInizializer {
    @PostConstruct
    private void initFirestore() throws IOException{
        
    InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("private-key-firestore.json");

    FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            //.setDatabaseUrl("https://post-1d502.firebaseio.com/")
            .build();

    if(FirebaseApp.getApps().isEmpty()){//si esta vacio no haga esto
        FirebaseApp.initializeApp(options);        
        }
    }
    
    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
}
