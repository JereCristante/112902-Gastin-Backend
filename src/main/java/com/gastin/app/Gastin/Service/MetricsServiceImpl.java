package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.ListMovementsDTO;
import com.gastin.app.Gastin.DTO.TransactionsMetricDTO;
import com.gastin.app.Gastin.DTO.UsersCreatedDTO;
import com.gastin.app.Gastin.Repository.MetricsRepository;
import com.gastin.app.Gastin.Repository.MovementRepository;
import com.gastin.app.Gastin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@Service
public class MetricsServiceImpl implements MetricsService{
    @Autowired
    private MetricsRepository metricsRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<TransactionsMetricDTO> getTransactionsMetric() {
        return metricsRepository.getTransactionsMetric();
    }
    @Override
    public List<UsersCreatedDTO> getUsersCreatedMetric() {
        return userRepository.getUsersCreatedMetric();
    }
}
