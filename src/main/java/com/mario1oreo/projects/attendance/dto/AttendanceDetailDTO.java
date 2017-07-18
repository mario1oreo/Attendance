package com.mario1oreo.projects.attendance.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by mario1oreo on 2017/6/26 22:45.
 */
@Data
public class AttendanceDetailDTO {

    private Integer id;
    private String employeeId;
    private LocalDate attendanceDate;
    private LocalTime attendanceTime;
    private String attendanceType;
    private String attendanceStatus;
    private Boolean isHoliday;
}
