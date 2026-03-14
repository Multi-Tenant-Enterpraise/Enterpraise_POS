package com.mis.buss.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.buss.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{ 
	List<Category> findByStoreId(Long storeId); 

}