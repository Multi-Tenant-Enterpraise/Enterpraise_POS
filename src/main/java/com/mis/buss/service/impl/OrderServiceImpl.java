package com.mis.buss.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mis.buss.mapper.OrderMapper;
import com.mis.buss.model.Branch;
import com.mis.buss.model.Customer;
import com.mis.buss.model.Order;
import com.mis.buss.model.OrderItem;
import com.mis.buss.model.Product;
import com.mis.buss.model.User;
import com.mis.buss.payload.dto.OrderDTO;
import com.mis.buss.repository.BranchRepository;
import com.mis.buss.repository.CustomerRepository;
import com.mis.buss.repository.OrderRepository;
import com.mis.buss.repository.ProductRepository;
import com.mis.buss.repository.UserRepository;
import com.mis.buss.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BranchRepository branchRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDTO createOrder(OrderDTO dto) throws Exception {

        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new Exception("Order must contain at least one item");
        }

        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new Exception("Branch not found"));

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new Exception("Customer not found"));

        User cashier = userRepository.findById(dto.getCashierId())
                .orElseThrow(() -> new Exception("Cashier not found"));

        Order order = OrderMapper.toEntity(dto);

        order.setBranch(branch);
        order.setCustomer(customer);
        order.setCashier(cashier);

        List<OrderItem> items = dto.getItems().stream().map(itemDto -> {

            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            return OrderItem.builder()
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .price(product.getSellingPrice() * itemDto.getQuantity())
                    .order(order)
                    .build();

        }).collect(Collectors.toList());

        double total = items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();

        order.setTotalAmount(total);
        order.setItems(items);

        Order saved = orderRepository.save(order);

        return OrderMapper.toDTO(saved);
    }

    @Override
    public OrderDTO getOrderById(Long id) throws Exception {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Order not found"));

        return OrderMapper.toDTO(order);
    }

    @Override
    public List<OrderDTO> getOrdersByBranch(Long branchId) {

        return orderRepository.findByBranchId(branchId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByCashier(Long cashierId) {

        return orderRepository.findByCashierId(cashierId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) throws Exception {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Order not found"));

        orderRepository.delete(order);
    }

    @Override
    public List<OrderDTO> getTodayOrdersByBranch(Long branchId) throws Exception {

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        return orderRepository
                .findByBranchIdAndCreatedAtBetween(branchId, start, end)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) throws Exception {

        return orderRepository
                .findByCustomerId(customerId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId) throws Exception {

        return orderRepository
                .findTop5ByBranchIdOrderByCreatedAtDesc(branchId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }
}