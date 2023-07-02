package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.Model.Report;
import com.gastin.app.Gastin.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

}
