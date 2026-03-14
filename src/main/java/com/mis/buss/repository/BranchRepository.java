package com.mis.buss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.buss.domain.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long>{
    List<Branch> findByStoreId(Long storeId);
}