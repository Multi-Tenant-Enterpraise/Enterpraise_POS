package com.mis.buss.service;



import java.util.List;

import com.mis.buss.payload.dto.InventoryDTO;

public interface InventoryService{

    InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception;
    InventoryDTO updateInventory(Long id,InventoryDTO inventoryDTO) throws Exception;
    void deleteInventory(Long id) throws Exception;
    InventoryDTO getInventoryById(Long id) throws Exception;
    InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId);
    List<InventoryDTO>getInventoryByBranchId(Long branchId);

}
