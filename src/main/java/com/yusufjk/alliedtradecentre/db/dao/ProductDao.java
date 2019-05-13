package com.yusufjk.alliedtradecentre.db.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.yusufjk.alliedtradecentre.db.config.FirestoreConfig;
import com.yusufjk.alliedtradecentre.db.model.Product;
import com.yusufjk.alliedtradecentre.db.repository.DBServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class ProductDao implements DBServiceRepository {
    private final FirestoreConfig firestoreConfig;

    @Autowired
    public ProductDao(FirestoreConfig firestoreConfig) {
        this.firestoreConfig = firestoreConfig;
    }

    public void saveProduct(Product product) {
        CollectionReference docRef = firestoreConfig.getDb();
        ApiFuture<DocumentReference> result = docRef.add(product);
        try {
            log.info("Product successfully saved in database with ID : " + result.get().getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    public List<Product> getAllData() {
        List<Product> allProducts = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> data = firestoreConfig.getDb().get();
            QuerySnapshot querySnapshot = data.get();
            List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot d : documentSnapshotList) {
                Product product = d.toObject(Product.class);
                product.setId(d.getId());
                allProducts.add(product);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    @Override
    public void updateProduct(Product product) {
        ApiFuture<WriteResult> result = firestoreConfig.getDb().document(product.getId()).set(product);
        try {
            if (result.isDone()) {
                System.out.println("Product successfully updated");
            }
        } catch (Exception e) {
            System.out.println("An error occured while saving the data " + e.getMessage());
        }
    }
}
