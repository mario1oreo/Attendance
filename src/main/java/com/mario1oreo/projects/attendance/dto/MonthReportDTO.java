package com.mario1oreo.projects.attendance.dto;

import lombok.Data;

/**
 * Created by mario1oreo on 2017/6/26 22:40.
 */
@Data
public class MonthReportDTO {

    private Integer id;
    private String employeeId;
    private String reportMonth;
    private Integer exceptionTimes;
    private String exceptionType;
}
