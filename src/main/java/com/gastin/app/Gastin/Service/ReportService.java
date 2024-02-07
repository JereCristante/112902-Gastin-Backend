package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.MovementDTO;
import com.gastin.app.Gastin.DTO.ReportDTO;

import java.util.List;

public interface ReportService {
    public ReportDTO createReport(Long usuario_id, ReportDTO reportDTO);
    public List<ReportDTO> getReports();
}
