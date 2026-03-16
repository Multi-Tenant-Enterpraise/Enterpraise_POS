package com.mis.buss.service;

public interface EmployeeService{
    UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception;
    UserDto createBranchEmployee(UserDto employee, Long storeId) throws Exception;
    User updateEmplyee(Long employeeId,User employeeDetails);
    void delereEmployee(Long employeeId);
    List<User> findStoreEmployees(Long storeId,UserRole role);
    List<User> findBranchEmplyees(Long branchId,UserRole role);
}