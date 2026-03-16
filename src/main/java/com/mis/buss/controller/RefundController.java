package com.mis.buss.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mis.buss.payload.dto.RefundDTO;
import com.mis.buss.payload.response.ApiResponse;
import com.mis.buss.service.RefundService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refunds")
public class RefundController {

    private final RefundService refundService;

    @PostMapping
    public ResponseEntity<RefundDTO> createRefund(
            @RequestBody RefundDTO dto) throws Exception {

        return ResponseEntity.ok(refundService.createRefund(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefundDTO> getRefund(
            @PathVariable Long id) throws Exception {

        return ResponseEntity.ok(refundService.getRefundById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<RefundDTO>> getRefundsByOrder(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(refundService.getRefundsByOrderId(orderId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRefund(
            @PathVariable Long id) throws Exception {

        refundService.deleteRefund(id);

        ApiResponse response = new ApiResponse();
        response.setMessage("Refund deleted successfully");

        return ResponseEntity.ok(response);
    }
}