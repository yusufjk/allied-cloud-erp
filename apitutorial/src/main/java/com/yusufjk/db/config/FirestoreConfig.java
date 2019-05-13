package com.yusufjk.db.config;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FirestoreConfig {
    @Value("${firestore.collection}")
    private String collectionReference;
    @Value("${firestore.key}")
    private String key;
    @Value("${firestore.url}")
    private String databaseUrl;
    private Firestore db;

    public String getDBInfo() {
        return this.collectionReference + this.key + this.databaseUrl;
    }

    public DocumentReference getDocumentReference() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(key);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();
        db = FirestoreClient.getFirestore(FirebaseApp.initializeApp(options));
        return db.collection(collectionReference).document();
    }

    public ApiFuture<QuerySnapshot> getQuerySnapshot() {
        return db.collection(collectionReference).get();
    }
}