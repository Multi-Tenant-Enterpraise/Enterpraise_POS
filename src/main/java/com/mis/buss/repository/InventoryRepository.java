package com.zosh.repository;

import com.zosh.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface InventoryRepository exten
ds JpaRepository<Inventory,Long>{

    Inventory findByProductIdAndBranchId(Long productId, branchId);
    List<Inventory>findByBranchId(Long branchId);

}