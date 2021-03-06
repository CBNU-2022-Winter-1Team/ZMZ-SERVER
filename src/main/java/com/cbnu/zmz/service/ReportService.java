package com.cbnu.zmz.service;

import com.cbnu.zmz.dto.ReportDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.entity.Report;
import com.cbnu.zmz.entity.User;

import java.util.List;


public interface ReportService {
    StatusDTO report_list(ReportDTO reportDTO);
    List<ReportDTO> callReportList();

    default Report dtoToEntity(ReportDTO reportDTO){ //entity에 있는애들 DTO방식으로 가져오면됨
        Report report = Report.builder()
                .report_id(reportDTO.getReport_id())
                .user(User.builder().user_id(reportDTO.getUser_id()).build())
                .report_period(reportDTO.getReport_period())
                .report_reason(reportDTO.getReportSet())
                .regDate(reportDTO.getRegDate())
                .post_id(reportDTO.getPost_id())
                .report_title(reportDTO.getReport_title())
                .report_content(reportDTO.getReport_content())
                .build();
        return report;
    }

    default ReportDTO entityToDTO(Report report){ //DTO에 있는 애들 entity방식대로 가져오면됨
        ReportDTO reportDTO = ReportDTO.builder()
                .user_id(report.getUser().getUser_id())
                .report_id(report.getReport_id())
                .report_period(report.getReport_period())
                .reportSet(report.getReport_reason())
                .regDate(report.getRegDate())
                .post_id(report.getPost_id())
                .report_title(report.getReport_title())
                .report_content(report.getReport_content())
                .build();
        return reportDTO;
    }
}
