import com.zosh.service.InventoryService;

import java.util.List;

public class InventoryServiceImpl implements InventoryService{
private final InventoryRepository inventoryRepository;

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception{

        Branch branch=branchRepository.findById(inventoryDTO.getBranchById())
                    .orElseThrow(
                    ()->new Exception("branch not exist...")
            );
            Product product=productRepository.findById(inventoryDTO.getBranchById())
                    .orElseThrow(
                    ()->new Exception("product not exist...")
            );

Inventory inventory=InventoryMapper.toEntity(inventoryDTO,branch,product);
Inventory savedInventory=inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO){
            Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()->new Exception("inventory not found...")
            );
            inventory.setQuantity(inventoryDTO.getQuantity());


            Inventory updatedInventory=inventoryRepository.save(inventory);
            return InventoryMapper.toDTO(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id){
         Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()->new Exception("inventory not found...")
            );


    } 

    @Override
    public InventoryDTO getInventoryById(Long id) throws Exception{
         Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()->new Exception("inventory not found...")
            );
        return InventoryMapper.toDTO(inventory);
    }
    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId,Long branchId){
        Inventory inventory=inventoryRepository.findByProductIdAndBranchId(productId,branchId);

        return InventoryMapper.toDTO(inventory);
    }

@Override 

public List<InventoryDTO> getInventoryByBranchId(Long branchId){
    List<Inventory> inventories=inventoryRepository.findByBranchId(branchId);
    return inventories.stream().map(
                InventoryMapper::toDTO
            ).collect(collectors.toList());

       }
  }