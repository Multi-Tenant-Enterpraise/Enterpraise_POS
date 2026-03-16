package com.mis.buss.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mis.buss.mapper.CustomerMapper;
import com.mis.buss.model.Customer;
import com.mis.buss.payload.dto.CustomerDTO;
import com.mis.buss.repository.CustomerRepository;
import com.mis.buss.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {

        Customer customer = CustomerMapper.toEntity(dto);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toDTO(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) throws Exception {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Customer not found"));

        customer.setFullName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());

        Customer updated = customerRepository.save(customer);

        return CustomerMapper.toDTO(updated);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Customer not found"));

        customerRepository.delete(customer);
    }

    @Override
    public CustomerDTO getCustomer(Long id) throws Exception {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Customer not found"));

        return CustomerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {

        return customerRepository
                .findByFullNameContainingIgnoreCase(keyword)
                .stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }
}