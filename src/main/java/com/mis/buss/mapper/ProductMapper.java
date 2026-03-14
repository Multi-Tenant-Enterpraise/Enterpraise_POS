
//
package com.mis.buss.mapper;

import com.mis.buss.model.Product;
import com.mis.buss.model.Store;
import com.mis.buss.payload.dto.ProductDTO;

public class ProductMapper {

    public static Product toEntity(ProductDTO productDTO, Store store) {

        return Product.builder()
                .name(productDTO.getName())
                .sku(productDTO.getSku())
                .description(productDTO.getDescription())
                .mrp(productDTO.getMrp())
                .sellingPrice(productDTO.getSellingPrice())
                .brand(productDTO.getBrand())
                .image(productDTO.getImage())
                .store(store)
                .build();
    }

    public static ProductDTO toDTO(Product product) {

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .image(product.getImage())
                .storeId(product.getStore().getId())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
              //.category(Category)
                .build();
    }
}
