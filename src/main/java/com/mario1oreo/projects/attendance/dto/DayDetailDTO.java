package com.mario1oreo.projects.attendance.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by mario1oreo on 2017/6/26 23:03.
 */
@Data
public class DayDetailDTO {

    private String department;
    private String employeeId;
    private String name;
    private LocalDate date;
    private Boolean isHoliday;
    private LocalTime onAttendanceTime;
    private LocalTime offAttendanceTime;
    private String onStatus;
    private String offStatus;
}
