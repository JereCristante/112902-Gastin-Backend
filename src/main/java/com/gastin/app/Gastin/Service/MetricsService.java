package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.AccountDTO;
import com.gastin.app.Gastin.DTO.TransactionsMetricDTO;
import com.gastin.app.Gastin.DTO.UsersCreatedDTO;

import java.util.List;

public interface MetricsService {

    public List<TransactionsMetricDTO> getTransactionsMetric();
    public List<UsersCreatedDTO> getUsersCreatedMetric();
}
