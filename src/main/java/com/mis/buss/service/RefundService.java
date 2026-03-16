package com.mis.buss.service;

import java.util.List;

import com.mis.buss.payload.dto.RefundDTO;

public interface RefundService {

    RefundDTO createRefund(RefundDTO dto) throws Exception;

    RefundDTO getRefundById(Long id) throws Exception;

    List<RefundDTO> getRefundsByOrderId(Long orderId);

    void deleteRefund(Long id) throws Exception;
}