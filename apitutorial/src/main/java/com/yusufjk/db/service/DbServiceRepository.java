package com.yusufjk.db.service;

import com.yusufjk.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DbServiceRepository {
    void saveProduct(Product product);
    List<Product> getAllData();
}
