package com.cbnu.zmz.controller;

import com.cbnu.zmz.dto.ReportDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.service.ReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/reportList")
    public ResponseEntity<StatusDTO> report_list(@RequestBody ReportDTO reportDTO){
        log.info("----------------report join!!-------------------------------");


        return new ResponseEntity<StatusDTO>(reportService.report_list(reportDTO), HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<ReportDTO>> show_report_list(){
        log.info("----------------show list of reports!!-------------------------------");
        return new ResponseEntity<>(reportService.callReportList(),HttpStatus.OK);
    }
}
