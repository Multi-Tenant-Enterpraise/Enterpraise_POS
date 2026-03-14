package com.mis.buss.mapper;

import com.mis.buss.model.Category;
import com.mis.buss.payload.dto.CategoryDTO;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {

        if (category == null) {
            return null;
        }

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(
                        category.getStore() != null
                                ? category.getStore().getId()
                                : null
                )
                .build();
    }
}