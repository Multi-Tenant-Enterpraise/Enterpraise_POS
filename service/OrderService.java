package com.mis.buss.service;

import java.util.List;

import com.mis.buss.payload.dto.OrderDTO;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO) throws Exception;

    OrderDTO getOrderById(Long id) throws Exception;

    List<OrderDTO> getOrdersByBranch(Long branchId);

    List<OrderDTO> getOrdersByCashier(Long cashierId);

    void deleteOrder(Long id) throws Exception;

    List<OrderDTO> getTodayOrdersByBranch(Long branchId) throws Exception;

    List<OrderDTO> getOrdersByCustomerId(Long customerId) throws Exception;

    List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId) throws Exception;
}