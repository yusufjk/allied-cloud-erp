package com.yusufjk.alliedtradecentre.db.repository;

import com.yusufjk.alliedtradecentre.db.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DBServiceRepository {
    void saveProduct(Product product);

    List<Product> getAllData();

    void updateProduct(Product product);
}
