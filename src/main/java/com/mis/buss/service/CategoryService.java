package com.mis.buss.service;

import java.util.List;

import com.mis.buss.exceptions.UserException;
import com.mis.buss.payload.dto.CategoryDTO;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO dto) throws UserException;

    List<CategoryDTO> getCategoriesByStore(Long storeId);

    CategoryDTO updateCategory(Long id, CategoryDTO dto) throws UserException;

    void deleteCategory(Long id) throws UserException;
}