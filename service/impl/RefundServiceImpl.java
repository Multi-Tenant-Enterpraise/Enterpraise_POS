package com.mis.buss.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mis.buss.mapper.RefundMapper;
import com.mis.buss.model.Order;
import com.mis.buss.model.Refund;
import com.mis.buss.payload.dto.RefundDTO;
import com.mis.buss.repository.OrderRepository;
import com.mis.buss.repository.RefundRepository;
import com.mis.buss.service.RefundService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;

    @Override
    public RefundDTO createRefund(RefundDTO dto) throws Exception {

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new Exception("Order not found"));

        Refund refund = RefundMapper.toEntity(dto, order);

        Refund saved = refundRepository.save(refund);

        return RefundMapper.toDTO(saved);
    }

    @Override
    public RefundDTO getRefundById(Long id) throws Exception {

        Refund refund = refundRepository.findById(id)
                .orElseThrow(() -> new Exception("Refund not found"));

        return RefundMapper.toDTO(refund);
    }

    @Override
    public List<RefundDTO> getRefundsByOrderId(Long orderId) {

        return refundRepository.findByOrderId(orderId)
                .stream()
                .map(RefundMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRefund(Long id) throws Exception {

        Refund refund = refundRepository.findById(id)
                .orElseThrow(() -> new Exception("Refund not found"));

        refundRepository.delete(refund);
    }
}