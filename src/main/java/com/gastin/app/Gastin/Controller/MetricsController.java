package com.gastin.app.Gastin.Controller;

import com.gastin.app.Gastin.DTO.CategoryTotalDTO;
import com.gastin.app.Gastin.DTO.DatesFilterDTO;
import com.gastin.app.Gastin.DTO.TransactionsMetricDTO;
import com.gastin.app.Gastin.DTO.UsersCreatedDTO;
import com.gastin.app.Gastin.Service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/metrics")
@CrossOrigin
public class MetricsController {
    @Autowired
    private MetricsService metricsService;
    @GetMapping ("/transactions")
    public ResponseEntity<List<TransactionsMetricDTO>> getTransactionsByDay(){
        return new ResponseEntity<List<TransactionsMetricDTO>>(metricsService.getTransactionsMetric(), HttpStatus.OK);
    }
    @GetMapping ("/new-users")
    public ResponseEntity<List<UsersCreatedDTO>> getNewUsersByMonth(){
        return new ResponseEntity<List<UsersCreatedDTO>>(metricsService.getUsersCreatedMetric(), HttpStatus.OK);
    }
}
