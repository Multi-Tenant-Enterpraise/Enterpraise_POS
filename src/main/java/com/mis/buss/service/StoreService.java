package com.mis.buss.service;



import java.util.List;

import com.mis.buss.domain.StoreStatus;
import com.mis.buss.exceptions.UserException;
import com.mis.buss.model.Store;
import com.mis.buss.model.User;
import com.mis.buss.payload.dto.StoreDTO;

public interface StoreService {
	StoreDTO createdStore(StoreDTO storeDTO,User user);
	StoreDTO getStoreById(Long id) throws Exception;
	List<StoreDTO> getAllStores();
	Store getStoreByAdmin() throws UserException;
	StoreDTO updateStore(Long id,StoreDTO storeDTO) throws Exception;
	void deleteStore(Long id) throws UserException;
	StoreDTO getStoreByEmployee() throws UserException;
	
	StoreDTO moderateStore(Long id, StoreStatus status) throws Exception;
}
