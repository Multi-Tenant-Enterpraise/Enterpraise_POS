package com.mis.buss.service;

import java.util.List;

import com.mis.buss.payload.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO dto);

    CustomerDTO updateCustomer(Long id, CustomerDTO dto) throws Exception;

    void deleteCustomer(Long id) throws Exception;

    CustomerDTO getCustomer(Long id) throws Exception;

    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> searchCustomers(String keyword);
}