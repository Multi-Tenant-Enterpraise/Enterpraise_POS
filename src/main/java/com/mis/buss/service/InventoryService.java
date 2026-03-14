package com.zosh.service;

import com.zosh.payload.dto.InventoryDTO;

import java.util.List;

public interface InventoryService{

    InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception;
    InventoryDTO updateInventory(Long id,InventoryDTO inventoryDTO);
    void deleteInventory(Long id);
    InventoryDTO getInventoryById(Long id);
    InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId);
    List<InventoryDTO>getInventoryByBranchId(Long branchId);

}