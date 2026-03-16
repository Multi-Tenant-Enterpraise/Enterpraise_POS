package com.mis.buss.service;

import java.util.List;

import com.mis.buss.payload.dto.PaymentSummaryDTO;
import com.mis.buss.payload.dto.ShiftReportDTO;

public interface ShiftReportService {

    ShiftReportDTO createShiftReport(ShiftReportDTO dto) throws Exception;

    ShiftReportDTO getShiftReport(Long id) throws Exception;

    List<ShiftReportDTO> getReportsByBranch(Long branchId);

    List<ShiftReportDTO> getReportsByEmployee(Long employeeId);

    PaymentSummaryDTO getPaymentSummary(Long branchId);
}