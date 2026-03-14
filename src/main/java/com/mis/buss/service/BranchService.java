package com.mis.buss.service;

import java.util.List;

import com.mis.buss.exceptions.UserException;
import com.mis.buss.payload.dto.BranchDTO;

public interface BranchService{
    BranchDTO createBranch(BranchDTO branchDTO)throws UserException;
    BranchDTO updateBranch(Long id,BranchDTO branchDTO)throws Exception;
    void deleteBranch(Long id) throws Exception;
    List<BranchDTO> getAllBranchesByStoreId(Long storeId);
    BranchDTO getAllBranchesByStoreId1(Long storeId);
    BranchDTO getBranchById(Long id) throws Exception;
}
