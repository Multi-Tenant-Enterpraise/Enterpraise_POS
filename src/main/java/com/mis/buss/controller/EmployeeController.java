package com.mis.buss.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mis.buss.payload.dto.EmployeeDTO;
import com.mis.buss.payload.response.ApiResponse;
import com.mis.buss.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(
            @RequestBody EmployeeDTO dto) throws Exception {

        return ResponseEntity.ok(employeeService.createEmployee(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDTO dto) throws Exception {

        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(
            @PathVariable Long id) throws Exception {

        employeeService.deleteEmployee(id);

        ApiResponse response = new ApiResponse();
        response.setMessage("Employee deleted successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(
            @PathVariable Long id) throws Exception {

        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByBranch(
            @PathVariable Long branchId) {

        return ResponseEntity.ok(employeeService.getEmployeesByBranchId(branchId));
    }
}