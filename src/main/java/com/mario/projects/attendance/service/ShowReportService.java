package com.mario.projects.attendance.service;

import com.mario.projects.attendance.dto.ReportDetailDTO;

import java.util.List;

/**
 * Created by mario1oreo on 2017/7/17 0:34.
 */
public interface ShowReportService {

    List<ReportDetailDTO> listExceptionAttendance(String startDate, String endDate);
    List<ReportDetailDTO> listTotalReport(String startDate, String endDate);
}
