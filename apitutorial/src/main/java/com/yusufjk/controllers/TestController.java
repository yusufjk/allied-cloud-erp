package com.yusufjk.controllers;

import com.yusufjk.db.config.FirestoreConfig;
import com.yusufjk.db.dao.ProductDao;
import com.yusufjk.db.service.DbServiceRepository;
import com.yusufjk.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    private final FirestoreConfig firestoreConfig;
    private final DbServiceRepository dbService;

    @Autowired
    public TestController(ProductDao dbService, FirestoreConfig firestoreConfig) {
        this.dbService = dbService;
        this.firestoreConfig = firestoreConfig;
    }

    @PostMapping("/save-product")
    public ResponseEntity<Boolean> saveProduct(@RequestBody Product product) {
        dbService.saveProduct(product);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping("/get-all-data")
    public List<Product> getAllData() {
        return dbService.getAllData();
    }

    @PostMapping("/delete-product?id")
    public void deleteProduct() {

    }

    @GetMapping("/get-db-data")
    public String getDB() {
        return firestoreConfig.getDBInfo();
    }
}
