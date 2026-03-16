package com.mis.buss.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mis.buss.mapper.ProductMapper;
import com.mis.buss.model.Product;
import com.mis.buss.model.Store;
import com.mis.buss.model.User;
import com.mis.buss.payload.dto.ProductDTO;
import com.mis.buss.repository.ProductRepository;
import com.mis.buss.repository.StoreRepository;
import com.mis.buss.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) {

        Store store = storeRepository.findById(productDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        
        checkAuthority(user, store);

        Product product = ProductMapper.toEntity(productDTO, store);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        checkAuthority(user, product.getStore());

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setImage(productDTO.getImage());
        product.setBrand(productDTO.getBrand());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        checkAuthority(user, product.getStore());

        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {

        List<Product> products = productRepository.findByStoreId(storeId);

        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {

        List<Product> products = productRepository.searchByKeyword(storeId, keyword);

        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    private void checkAuthority(User user, Store store) {

        boolean isAdmin = user.getRole().name().equals("ROLE_STORE_ADMIN");
        boolean isManager = user.getRole().name().equals("ROLE_STORE_MANAGER");

        boolean isSameStore = store.getStoreAdmin() != null &&
                user.getId().equals(store.getStoreAdmin().getId());

        if (!((isAdmin && isSameStore) || isManager)) {
            throw new RuntimeException("You don't have permission for this store");
        }
    }
}