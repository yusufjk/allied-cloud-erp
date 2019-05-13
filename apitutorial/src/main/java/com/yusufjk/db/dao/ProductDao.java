package com.yusufjk.db.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.yusufjk.db.config.FirestoreConfig;
import com.yusufjk.db.service.DbServiceRepository;
import com.yusufjk.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ProductDao implements DbServiceRepository {
    @Autowired
    private FirestoreConfig firestoreConfig;

    public void saveProduct(Product product) {
        DocumentReference docRef;
        try {
            docRef = firestoreConfig.getDocumentReference();
            ApiFuture<WriteResult> result = docRef.set(product);
            if (result.isDone()) {
                System.out.println("Product successfully saved in database");
            }
        } catch (Exception e) {
            System.out.println("An error occured while saving the data " + e.getMessage());
        }
    }

    public List<Product> getAllData() {
        List<Product> allProducts = new ArrayList<>();
        ApiFuture<QuerySnapshot> data = firestoreConfig.getQuerySnapshot();
        try {
            QuerySnapshot querySnapshot = data.get();
            List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot d : documentSnapshotList) {
                Product product = d.toObject(Product.class);
                allProducts.add(product);
                System.out.println(product.getProductName() + " " + product.getQty() + " " + product.getSize() + " " + product.getStock());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allProducts;
    }
}
