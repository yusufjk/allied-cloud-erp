package com.yusufjk.alliedtradecentre.controller;

import com.yusufjk.alliedtradecentre.db.config.FirestoreConfig;
import com.yusufjk.alliedtradecentre.db.dao.ProductDao;
import com.yusufjk.alliedtradecentre.db.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ApiController {
    private final FirestoreConfig firestoreConfig;
    private final ProductDao productDao;

    @Autowired
    public ApiController(ProductDao productDao, FirestoreConfig firestoreConfig) {
        this.productDao = productDao;
        this.firestoreConfig = firestoreConfig;
    }

    @PostMapping("/save-product")
    public ResponseEntity<Boolean> saveProduct(@RequestBody Product product) {
        productDao.saveProduct(product);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/update-product")
    public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {
        productDao.updateProduct(product);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping("/get-all-data")
    public List<Product> getAllData() {
        return productDao.getAllData();
    }

    @PostMapping("/delete-product?id")
    public void deleteProduct() {

    }

    @GetMapping("/get-db-data")
    public String getDB() {
        return firestoreConfig.getDBInfo();
    }
}
