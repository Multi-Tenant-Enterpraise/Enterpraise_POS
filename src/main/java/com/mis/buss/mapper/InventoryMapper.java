package com.mis.buss.mapper;




import com.mis.buss.domain.Branch;
import com.mis.buss.model.Inventory;
import com.mis.buss.model.Product;
import com.mis.buss.payload.dto.InventoryDTO;

public class InventoryMapper{
    public static InventoryDTO toDTO(Inventory inventory){
        return InventoryDTO.builder()
        		.id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .productId(inventory.getProduct().getId())
                .quantity(inventory.getQuantity())
                .lastUpdate(inventory.getLastUpdate())
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

<<<<<<< HEAD
    private static class Inventory {

        public Inventory() {
        }
    }

}
=======
}
>>>>>>> cde2dc63f8699b0b7ea72b180f926b9fbbd41577
