package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.MovementDTO;
import com.gastin.app.Gastin.DTO.ReportDTO;

public interface ReportService {
    public ReportDTO createReport(Long usuario_id, ReportDTO reportDTO);
}
