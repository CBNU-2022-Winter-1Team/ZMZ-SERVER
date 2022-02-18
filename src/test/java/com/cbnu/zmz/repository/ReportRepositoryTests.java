package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.Report;
import com.cbnu.zmz.entity.Report_Kinds;
import com.cbnu.zmz.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReportRepositoryTests {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void testClass() {
        System.out.println(reportRepository.getClass().getName());
    }

    @Test
    public void testInsertDummy() {
        Optional<User> result = userRepository.findById("test...1");
        User user = result.get();

        IntStream.rangeClosed(1, 100).forEach((i -> {
            Report report = Report.builder()
                    .user(user)
                    .report_period((long) i)
                    .regDate(LocalDateTime.now())
                    .build();
            report.addReportRole(Report_Kinds.ETC);
            reportRepository.save(report);
        }));
    }
}
