package com.yusufjk.alliedtradecentre.db.model;

import com.google.cloud.firestore.annotation.Exclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
class Godown {
    @Exclude
    private String gid;
    private String godownName;
    private String rackSection;
    private String shelfNo;
    private double qty;
}
