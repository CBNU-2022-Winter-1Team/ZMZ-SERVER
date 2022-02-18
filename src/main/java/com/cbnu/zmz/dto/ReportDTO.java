package com.cbnu.zmz.dto;

import com.cbnu.zmz.entity.Report_Kinds;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private String user_id;
    private Long report_id;
    private Long report_period;
    private Set<Report_Kinds> reportSet = new HashSet<>();
    private Long post_id;
    private LocalDateTime regDate;
}
