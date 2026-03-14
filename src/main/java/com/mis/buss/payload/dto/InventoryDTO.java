 import jakart.persistence.ManyToMany;
 import lombok.Data;
 
 import java.time.localDateTime;
 @Data
 @Builder
 public class InventoryDTO{
 
    private Long id;

    private Branch brancDTO;

    private Product productDTO;

    private Integer quantity;

    private localDateTime lastUpdate;
 }