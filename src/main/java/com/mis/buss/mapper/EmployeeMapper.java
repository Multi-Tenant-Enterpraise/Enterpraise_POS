
package com.mis.buss.mapper;

import com.mis.buss.model.Branch;
import com.mis.buss.model.Employee;
import com.mis.buss.payload.dto.EmployeeDTO;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {

        if(employee == null){
            return null;
        }

        return EmployeeDTO.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .role(employee.getRole())
                .salary(employee.getSalary())
                .branchId(employee.getBranch().getId())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

    public static Employee toEntity(EmployeeDTO dto, Branch branch){

        return Employee.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .role(dto.getRole())
                .salary(dto.getSalary())
                .branch(branch)
                .build();
    }
}