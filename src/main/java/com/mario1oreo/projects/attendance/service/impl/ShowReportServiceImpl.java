package com.mario1oreo.projects.attendance.service.impl;

import com.mario1oreo.projects.attendance.dao.MonthReportDAO;
import com.mario1oreo.projects.attendance.dto.ReportDetailDTO;
import com.mario1oreo.projects.attendance.service.ShowReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by mario1oreo on 2017/7/17 0:36.
 */
@Service
@Slf4j
public class ShowReportServiceImpl implements ShowReportService {

    @Autowired
    private MonthReportDAO monthReportDAO;

    private static DateTimeFormatter DF_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter DF_YYYY_MM = DateTimeFormatter.ofPattern("yyyy-MM");

    @Override
    public List<ReportDetailDTO> listExceptionAttendance(String startDate, String endDate) {
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(startDate.trim())) {
            startDate = DF_YYYY_MM.format(LocalDate.now()) + "-01";
        }
        if (StringUtils.isBlank(endDate) || StringUtils.isBlank(endDate.trim())) {
            endDate = DF_YYYY_MM_DD.format(LocalDate.now());
        }
        return monthReportDAO.listMonthReport(startDate, endDate);
    }

    @Override
    public List<ReportDetailDTO> listTotalReport(String startDate, String endDate) {
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(startDate.trim())) {
            startDate = DF_YYYY_MM.format(LocalDate.now()) + "-01";
        }
        if (StringUtils.isBlank(endDate) || StringUtils.isBlank(endDate.trim())) {
            endDate = DF_YYYY_MM_DD.format(LocalDate.now());
        }
        return monthReportDAO.listTotalReport(startDate, endDate);
    }
}
