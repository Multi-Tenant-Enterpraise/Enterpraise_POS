package com.mis.buss.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mis.buss.domain.UserRole;
import com.mis.buss.exceptions.UserException;
import com.mis.buss.mapper.CategoryMapper;
import com.mis.buss.model.Category;
import com.mis.buss.model.Store;
import com.mis.buss.model.User;
import com.mis.buss.payload.dto.CategoryDTO;
import com.mis.buss.repository.CategoryRepository;
import com.mis.buss.repository.StoreRepository;
import com.mis.buss.service.CategoryService;
import com.mis.buss.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) throws UserException {

        User user = userService.getCurrentUser();

        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        checkAuthority(user, store);

        Category category = Category.builder()
                .name(dto.getName())
                .store(store)
                .build();

        return CategoryMapper.toDTO(
                categoryRepository.save(category)
        );
    }

    @Override
    public List<CategoryDTO> getCategoriesByStore(Long storeId) {

        List<Category> categories =
                categoryRepository.findByStoreId(storeId);

        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) throws UserException {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not exist"));

        User user = userService.getCurrentUser();

        category.setName(dto.getName());

        checkAuthority(user, category.getStore());

        return CategoryMapper.toDTO(
                categoryRepository.save(category)
        );
    }

    @Override
    public void deleteCategory(Long id) throws UserException {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not exist"));

        User user = userService.getCurrentUser();

        checkAuthority(user, category.getStore());

        categoryRepository.delete(category);
    }

    private void checkAuthority(User user, Store store) {

        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = store.getStoreAdmin() != null &&
                user.getId().equals(store.getStoreAdmin().getId());

        if (!((isAdmin && isSameStore) || isManager)) {
            throw new RuntimeException(
                    "You don't have permission to manage this category"
            );
        }
    }
}