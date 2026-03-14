package com.zosh.controller;

import org.springframework.web.bind.annotation.*;
import com.zosh.payload.response.ApiResponse;
import com.zosh.service.InventoryService;
import com.zosh.payload.dto.InventoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Requestmapping("/api/inventories")
public class InventoryController{

private final InventoryService InventoryService;


@PostMapping()
public ResponseEntity<InventoryDTO> create(
    @RequestBody InventoryDTO inventoryDTO
    )throws Exception{

    return ResponseEntity.ok(InventoryService.createInventory(inventoryDTO));
}
@PutMapping("/{id}")
public ResponseEntity<InventoryDTO> update(
    @RequestBody InventoryDTO inventoryDTO,
    @PathVariable Long id
    )throws Exception{

    return ResponseEntity.ok(InventoryService.updateInventoryInventory(id,inventoryDTO));
}


@DeleteMapping("/{id}")
public ResponseEntity<ApiResponse> delete(
    
    @PathVariable Long id
    )throws Exception{
InventoryService.deleteInventoryInventory(id);

ApiResponse apiResponse=new ApiResponse();
apiResponse.setMessage("Inventory deleted");
    return ResponseEntity.ok(apiResponse);
}

@GetMapping("/branch/{branchId}/product/{productId}")
public ResponseEntity<List<InventoryDTO>> getInventoryByProductAndBranchId(
      @PathVariable Long branchId,
    @PathVariable Long productId
    )throws Exception{
        
    return ResponseEntity.ok(InventoryService.getallInventoryByBranchId(branchId));
}
@GetMapping("/branch/{branchId}")
public ResponseEntity<List<InventoryDTO>> getInventoryByBranch(
    
    @PathVariable Long branchId
    )throws Exception{
        
    return ResponseEntity.ok(InventoryService.getallInventoryBybranchId(branchId));
}

}