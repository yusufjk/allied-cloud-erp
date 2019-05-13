package com.yusufjk.alliedtradecentre.db.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FirestoreConfig {

    private final String collectionReference;

    private final String key;

    private final String databaseUrl;
    private final Firestore db;

    @Autowired
    public FirestoreConfig(@Value("${firestore.collection}") String collectionReference,
                           @Value("${firestore.key}") String key,
                           @Value("${firestore.url}") String databaseUrl) throws IOException {
        this.collectionReference = collectionReference;
        this.key = key;
        this.databaseUrl = databaseUrl;

        FileInputStream serviceAccount = new FileInputStream(key);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();
        db = FirestoreClient.getFirestore(FirebaseApp.initializeApp(options));
    }

    public String getDBInfo() {
        return this.collectionReference + this.key + this.databaseUrl;
    }

    public CollectionReference getDb() {
        return db.collection(collectionReference);
    }
}