package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.DTO.ListMovementsDTO;
import com.gastin.app.Gastin.DTO.TransactionsMetricDTO;
import com.gastin.app.Gastin.Model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MetricsRepository extends JpaRepository<Movement,Long> {

    @Query(nativeQuery = true,name = "Movement.TransactionMetrics")
    public List<TransactionsMetricDTO> getTransactionsMetric();

}
