package com.mis.buss.mapper;

import com.mis.buzz.model.Inventory;
import com.mis.buzz.model.InventoryDTO;

public class InventoryMapper{
    public static InventoryDTO toDTO(Inventory inventory){
        return InventoryDTO.builder()
            .id(inventory.getId())
            .branchId(inventory.getBranch().getId())
            .branch(inventory.getProduct().getId())
            .product(productMapper.toDTO(inventory.getProduct()))
            .quantity(inventory.getQuantity())
            .build();

    }



public static Inventory toEntity(InventoryDTO inventoryDTO,
                                    Branch branch,
                                    Product product){
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDTO.getQuantity())
                .build();
                                    }

    private static class Inventory {

        public Inventory() {
        }
    }

}