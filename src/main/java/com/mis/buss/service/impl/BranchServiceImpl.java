package com.mis.buss.service.impl;




import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mis.buss.exceptions.UserException;
import com.mis.buss.mapper.BranchMapper;
import com.mis.buss.model.Branch;
import com.mis.buss.model.Store;
import com.mis.buss.model.User;
import com.mis.buss.payload.dto.BranchDTO;
import com.mis.buss.repository.BranchRepository;
import com.mis.buss.repository.StoreRepository;
import com.mis.buss.service.BranchService;
import com.mis.buss.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService{
    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;
    

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) throws UserException{
        User currentUser=userService.getCurrentUser();
        Store store=storeRepository.findByStoreAdminId(currentUser.getId());
        Branch branch = BranchMapper.toEntity(branchDTO, store);
        Branch savedBranch = branchRepository.save(branch);
        return BranchMapper.toDTO(savedBranch);
    } 

    @Override
    public BranchDTO updateBranch(Long id,BranchDTO branchDTO)throws Exception{
    	Branch exiting = branchRepository.findById(id).orElseThrow(
    		    () -> new RuntimeException("branch not exist...")
    		);
        exiting.setName(branchDTO.getName());
        exiting.setWorkingDays(branchDTO.getWorkingDays());
        exiting.setEmail(branchDTO.getEmail());
        exiting.setPhone(branchDTO.getPhone());
        exiting.setAddress(branchDTO.getAddress());
        exiting.setOpenTime(branchDTO.getOpenTime());
        exiting.setCloseTime(branchDTO.getCloseTime());
        exiting.setUpdatedAt(LocalDateTime.now());
        Branch updatedBranch=branchRepository.save(exiting);
        return BranchMapper.toDTO(updatedBranch);
    }

    @Override
    public void deleteBranch(Long id) throws Exception{
    	Branch exiting = branchRepository.findById(id).orElseThrow(
    		    () -> new RuntimeException("branch not exist...")
    		);
        branchRepository.delete(exiting);
    }

    @Override
    public List<BranchDTO> getAllBranchesByStoreId(Long storeId){
        List<Branch> branches=branchRepository.findByStoreId(storeId);
        return branches.stream().map(BranchMapper::toDTO)
                .collect(Collectors.toList());
        
    }

    @Override
    public BranchDTO getBranchById(Long id)throws Exception{
    	Branch exiting = branchRepository.findById(id).orElseThrow(
    		    () -> new RuntimeException("branch not exist...")
    		);
        return BranchMapper.toDTO(exiting);
    }

	@Override
	public BranchDTO getAllBranchesByStoreId1(Long storeId) {
		// TODO Auto-generated method stub
		return null;
	}

}