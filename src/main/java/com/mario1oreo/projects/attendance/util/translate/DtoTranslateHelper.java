package com.mario1oreo.projects.attendance.util.translate;

import com.mario1oreo.projects.attendance.dto.AttendanceDetailDTO;
import com.mario1oreo.projects.attendance.dto.DayDetailDTO;
import com.mario1oreo.projects.attendance.dto.EmployeeInfoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario1oreo on 2017/6/29 0:25.
 */
public class DtoTranslateHelper {

    private DtoTranslateHelper() {
    }

    public static final String ATTENDANCE_TYPE_ON = "0";
    public static final String ATTENDANCE_TYPE_OFF = "1";

    public static List<AttendanceDetailDTO> dayDetailToAttenDetail(DayDetailDTO dayDetailDTO) {
        List<AttendanceDetailDTO> attenDetailList = new ArrayList<>();
        AttendanceDetailDTO attenDetailOn = new AttendanceDetailDTO();
        attenDetailOn.setAttendanceType(ATTENDANCE_TYPE_ON);
        attenDetailOn.setAttendanceStatus(dayDetailDTO.getOnStatus());
        attenDetailOn.setAttendanceTime(dayDetailDTO.getOnAttendanceTime());
        attenDetailOn.setAttendanceDate(dayDetailDTO.getDate());
        attenDetailOn.setEmployeeId(dayDetailDTO.getEmployeeId());
        attenDetailOn.setIsHoliday(dayDetailDTO.getIsHoliday());
        attenDetailList.add(attenDetailOn);

        AttendanceDetailDTO attenDetailOff = new AttendanceDetailDTO();
        attenDetailOff.setAttendanceType(ATTENDANCE_TYPE_OFF);
        attenDetailOff.setAttendanceStatus(dayDetailDTO.getOffStatus());
        attenDetailOff.setAttendanceTime(dayDetailDTO.getOffAttendanceTime());
        attenDetailOff.setAttendanceDate(dayDetailDTO.getDate());
        attenDetailOff.setEmployeeId(dayDetailDTO.getEmployeeId());
        attenDetailOff.setIsHoliday(dayDetailDTO.getIsHoliday());
        attenDetailList.add(attenDetailOff);
        return attenDetailList;
    }

    public static EmployeeInfoDTO dayDatailTOEmployeeInfo(DayDetailDTO dayDetailDTO) {
        EmployeeInfoDTO employeeInfoDTO = new EmployeeInfoDTO();
        employeeInfoDTO.setName(dayDetailDTO.getName());
        employeeInfoDTO.setDepartment(dayDetailDTO.getDepartment());
        employeeInfoDTO.setEmployeeId(dayDetailDTO.getEmployeeId());
        return employeeInfoDTO;
    }
}
