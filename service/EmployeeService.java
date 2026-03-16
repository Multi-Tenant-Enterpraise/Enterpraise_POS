package com.mis.buss.service;

import java.util.List;

import com.mis.buss.payload.dto.EmployeeDTO;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO) throws Exception;

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) throws Exception;

    void deleteEmployee(Long id) throws Exception;

    EmployeeDTO getEmployeeById(Long id) throws Exception;

    List<EmployeeDTO> getEmployeesByBranchId(Long branchId);
}