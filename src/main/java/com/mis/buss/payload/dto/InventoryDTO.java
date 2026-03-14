package com.mis.buss.payload.dto;



import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class InventoryDTO{

   private Long id;
 
   private Long branchId;
   private Long productId;
   private Integer quantity;
   private LocalDateTime lastUpdate;
}