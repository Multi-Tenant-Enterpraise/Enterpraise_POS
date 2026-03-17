package com.mis.buss.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mis.buss.mapper.EmployeeMapper;
import com.mis.buss.model.Branch;
import com.mis.buss.model.Employee;
import com.mis.buss.payload.dto.EmployeeDTO;
import com.mis.buss.repository.BranchRepository;
import com.mis.buss.repository.EmployeeRepository;
import com.mis.buss.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) throws Exception {

        Branch branch = branchRepository.findById(employeeDTO.getBranchId())
                .orElseThrow(() -> new Exception("Branch not found"));

        Employee employee = EmployeeMapper.toEntity(employeeDTO, branch);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) throws Exception {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new Exception("Employee not found"));

        employee.setFullName(employeeDTO.getFullName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setRole(employeeDTO.getRole());
        employee.setSalary(employeeDTO.getSalary());

        Employee updated = employeeRepository.save(employee);

        return EmployeeMapper.toDTO(updated);
    }

    @Override
    public void deleteEmployee(Long id) throws Exception {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new Exception("Employee not found"));

        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) throws Exception {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new Exception("Employee not found"));

        return EmployeeMapper.toDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByBranchId(Long branchId) {

        List<Employee> employees = employeeRepository.findByBranchId(branchId);

        return employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}