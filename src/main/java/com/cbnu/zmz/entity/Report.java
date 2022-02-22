package com.cbnu.zmz.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "report")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long report_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(length = 100, nullable = true)
    Long report_period;
    @Column(length = 100, nullable = true)
    Long post_id;
    @CreatedDate
    @Column(name = "regulated" , updatable = false)
    private LocalDateTime regDate;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Report_Kinds> report_reason = new HashSet<>();
    public void addReportRole(Report_Kinds report_kinds){
        report_reason.add(report_kinds);
    }




    @Column(length = 100, nullable = true)
    String report_title;

    @Column(length = 500, nullable = true)
    String report_content;
}
