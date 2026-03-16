
package com.mis.buss.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mis.buss.payload.dto.PaymentSummaryDTO;
import com.mis.buss.payload.dto.ShiftReportDTO;
import com.mis.buss.service.ShiftReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift-reports")
public class ShiftReportController {

    private final ShiftReportService shiftReportService;

    @PostMapping
    public ResponseEntity<ShiftReportDTO> createReport(
            @RequestBody ShiftReportDTO dto) throws Exception {

        return ResponseEntity.ok(
                shiftReportService.createShiftReport(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftReportDTO> getReport(
            @PathVariable Long id) throws Exception {

        return ResponseEntity.ok(
                shiftReportService.getShiftReport(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ShiftReportDTO>> getReportsByBranch(
            @PathVariable Long branchId) {

        return ResponseEntity.ok(
                shiftReportService.getReportsByBranch(branchId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ShiftReportDTO>> getReportsByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                shiftReportService.getReportsByEmployee(employeeId));
    }

    @GetMapping("/summary/{branchId}")
    public ResponseEntity<PaymentSummaryDTO> getPaymentSummary(
            @PathVariable Long branchId) {

        return ResponseEntity.ok(
                shiftReportService.getPaymentSummary(branchId));
    }
}