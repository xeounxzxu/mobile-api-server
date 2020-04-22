package com.app.api.domain.product;

import com.app.api.domain.image.Image;
import com.app.api.domain.store.Store;
import com.app.api.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "app_product")
public class Product extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "cnt")
    private Integer cnt;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "info" , columnDefinition = "TEXT")
    private String info;

    @Column(name = "isSold")
    private Boolean isSold;

    @ManyToOne
    @JoinColumn(name = "store_seq")
    private Store store;

    @OneToMany
    private List<Image> images;


    @Builder
    public Product(String title , Integer cnt , BigDecimal price , String info , Boolean isSold) {
       this.title = title;
       this.cnt = cnt;
       this.price = price;
       this.info = info;
       this.isSold = isSold;
    }


}
