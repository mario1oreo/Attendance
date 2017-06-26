package com.mario.projects.attendance.dto;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by mario1oreo on 2017/6/23 17:28.
 */
@Data
public class EmployeeInfoDTO {
    private Integer id;
    private String employeeId;
    private String name;
    private String department;
    private LocalDate onAttendanceStandard;
    private LocalDate offAttendanceStandard;
}
