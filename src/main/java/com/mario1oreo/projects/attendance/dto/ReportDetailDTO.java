package com.mario1oreo.projects.attendance.dto;

import lombok.Data;

/**
 * Created by mario1oreo on 2017/7/17 0:30.
 */
@Data
public class ReportDetailDTO {
    private String department;
    private String name;
    private String date;
    private String noon;
    private String status;
    private String times;
}
