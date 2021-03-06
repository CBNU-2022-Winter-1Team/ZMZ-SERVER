package com.cbnu.zmz.service;

import com.cbnu.zmz.dto.ReportDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.entity.Report;
import com.cbnu.zmz.repository.ReportRepository;
import com.cbnu.zmz.repository.UserRepository;
import jdk.jshell.Snippet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Override
    public StatusDTO report_list(ReportDTO reportDTO) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setSuccess(true);
        statusDTO.setMessage("report success!");
        statusDTO.setStatus(200);

        reportDTO.setRegDate(LocalDateTime.now());
        Report report = dtoToEntity(reportDTO);
        reportRepository.save(report);
        return statusDTO;
    }

    @Override
    public List<ReportDTO> callReportList(){
        List<Report> result = reportRepository.findAll();
        List<ReportDTO> reportList = new ArrayList<>();
        result.forEach(
                r -> {
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO = entityToDTO(r);
                    reportList.add(reportDTO);
                }
        );
        return reportList;
    }
}
