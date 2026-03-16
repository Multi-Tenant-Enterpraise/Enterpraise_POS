package com.mis.buss.service.impl;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mis.buss.mapper.InventoryMapper;
import com.mis.buss.model.Branch;
import com.mis.buss.model.Inventory;
import com.mis.buss.model.Product;
import com.mis.buss.payload.dto.InventoryDTO;
import com.mis.buss.repository.BranchRepository;
import com.mis.buss.repository.InventoryRepository;
import com.mis.buss.repository.ProductRepository;
import com.mis.buss.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{
private final InventoryRepository inventoryRepository;
private final BranchRepository branchRepository;
private final ProductRepository productRepository;

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception{

        Branch branch=branchRepository.findById(inventoryDTO.getBranchId())
                    .orElseThrow(
                    ()->new Exception("branch not exist...")
            );
            Product product=productRepository.findById(inventoryDTO.getBranchId())
                    .orElseThrow(
                    ()->new Exception("product not exist...")
            );

Inventory inventory=InventoryMapper.toEntity(inventoryDTO,branch,product);
Inventory savedInventory=inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) throws Exception{
            Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()->new Exception("inventory not found...")
            );
            inventory.setQuantity(inventoryDTO.getQuantity());


            Inventory updatedInventory=inventoryRepository.save(inventory);
            return InventoryMapper.toDTO(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) throws Exception{
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
            ).collect(Collectors.toList());

       }
  }
