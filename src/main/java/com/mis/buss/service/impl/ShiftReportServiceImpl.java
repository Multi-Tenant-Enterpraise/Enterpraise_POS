package com.mis.buss.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mis.buss.mapper.ShiftReportMapper;
import com.mis.buss.model.Branch;
import com.mis.buss.model.Employee;
import com.mis.buss.model.ShiftReport;
import com.mis.buss.payload.dto.PaymentSummaryDTO;
import com.mis.buss.payload.dto.ShiftReportDTO;
import com.mis.buss.repository.BranchRepository;
import com.mis.buss.repository.EmployeeRepository;
import com.mis.buss.repository.ShiftReportRepository;
import com.mis.buss.service.ShiftReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;

    @Override
    public ShiftReportDTO createShiftReport(ShiftReportDTO dto) throws Exception {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new Exception("Employee not found"));

        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new Exception("Branch not found"));

        ShiftReport report = ShiftReportMapper.toEntity(dto, employee, branch);

        ShiftReport saved = shiftReportRepository.save(report);

        return ShiftReportMapper.toDTO(saved);
    }

    @Override
    public ShiftReportDTO getShiftReport(Long id) throws Exception {

        ShiftReport report = shiftReportRepository.findById(id)
                .orElseThrow(() -> new Exception("Shift report not found"));

        return ShiftReportMapper.toDTO(report);
    }

    @Override
    public List<ShiftReportDTO> getReportsByBranch(Long branchId) {

        return shiftReportRepository.findByBranchId(branchId)
                .stream()
                .map(ShiftReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDTO> getReportsByEmployee(Long employeeId) {

        return shiftReportRepository.findByEmployeeId(employeeId)
                .stream()
                .map(ShiftReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentSummaryDTO getPaymentSummary(Long branchId) {

        List<ShiftReport> reports = shiftReportRepository.findByBranchId(branchId);

        double cash = reports.stream().mapToDouble(r -> r.getCashSales()).sum();
        double card = reports.stream().mapToDouble(r -> r.getCardSales()).sum();
        double upi = reports.stream().mapToDouble(r -> r.getUpiSales()).sum();

        return PaymentSummaryDTO.builder()
                .cashTotal(cash)
                .cardTotal(card)
                .upiTotal(upi)
                .totalSales(cash + card + upi)
                .build();
    }
}