package com.mis.buss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.buss.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{
	Store findByStoreAdminId(Long adminId);
}
