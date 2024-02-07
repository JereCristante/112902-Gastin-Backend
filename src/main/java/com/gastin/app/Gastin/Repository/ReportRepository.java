package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.Model.Report;
import com.gastin.app.Gastin.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    List<Report> findAll();
}
