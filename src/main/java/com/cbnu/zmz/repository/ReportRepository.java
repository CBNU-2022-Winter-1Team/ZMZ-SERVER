package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ReportRepository extends JpaRepository<Report, Long> {
}