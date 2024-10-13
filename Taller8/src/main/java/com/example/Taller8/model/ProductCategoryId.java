package com.example.Taller8.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ProductCategoryId {
	@Column(name = "product_id")
    private Long productId;

    @Column(name = "category_id")
    private Long categoryId;

}
