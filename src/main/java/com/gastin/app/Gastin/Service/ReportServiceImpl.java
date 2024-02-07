package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.ReportDTO;
import com.gastin.app.Gastin.Exceptions.ResourceNotFoundException;
import com.gastin.app.Gastin.Model.Report;
import com.gastin.app.Gastin.Model.User;
import com.gastin.app.Gastin.Repository.ReportRepository;
import com.gastin.app.Gastin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Override
    public ReportDTO createReport(Long usuario_id, ReportDTO reportDTO) {
        Report report = entityMapping(reportDTO);
        User user = userRepository.findById(usuario_id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id",usuario_id));
        report.setUser(user);
        Report newReport = reportRepository.save(report);
        return dtoMapping(newReport);
    }

    @Override
    public List<ReportDTO> getReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(report -> dtoMapping(report)).collect(Collectors.toList());
    }

    private ReportDTO dtoMapping(Report report){
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setObservation(report.getObservation());
        reportDTO.setType(report.getType());
        reportDTO.setState(report.getState());
        reportDTO.setScreen(report.getScreen());
        reportDTO.setDate(report.getDate());
        return reportDTO;
    }
    private Report entityMapping(ReportDTO reportDTO){
        Report report = new Report();
        report.setId(reportDTO.getId());
        report.setObservation(reportDTO.getObservation());
        report.setType(reportDTO.getType());
        report.setState(reportDTO.getState());
        report.setScreen(reportDTO.getScreen());
        report.setDate(reportDTO.getDate());
        return report;
    }
}
