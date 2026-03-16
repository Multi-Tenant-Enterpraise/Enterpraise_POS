package com.mis.buss.service;

import java.util.List;

import com.mis.buss.model.User;
import com.mis.buss.payload.dto.ProductDTO;

public interface ProductService {
	
	ProductDTO createProduct(ProductDTO productDTO, User user);
	ProductDTO updateProduct(Long id, ProductDTO productDTO, User user);
	void deleteProduct(Long id, User user);
	List<ProductDTO> getProductsByStoreId(Long storeId);
	List<ProductDTO> searchByKeyword(Long storeid,String keyword);
	
}
