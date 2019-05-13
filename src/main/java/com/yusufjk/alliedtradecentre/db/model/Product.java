package com.yusufjk.alliedtradecentre.db.model;

import com.google.cloud.firestore.annotation.Exclude;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Exclude
    private String id;
    private String productName;
    private List<Godown> godown;
    private String size;
    private int totalQty;
    private double price;
}
