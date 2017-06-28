package com.mario.projects.attendance.service;

import com.mario.projects.attendance.dto.AttendanceDetailDTO;
import com.mario.projects.attendance.dto.DayDetailDTO;

import java.util.List;

/**
 * Created by mario1oreo on 2017/6/29 0:15.
 */
public interface AttendanceDetailService {

    void saveAttendanceDetailByDayList(List<DayDetailDTO> list);
    void saveAttendanceDetail(List<AttendanceDetailDTO> list);
    void saveAttendanceDetail(AttendanceDetailDTO dto);
    List<AttendanceDetailDTO> listAll();
    Integer countAll();
}
