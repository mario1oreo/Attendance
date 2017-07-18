package com.mario1oreo.projects.attendance.dao;

import com.mario1oreo.projects.attendance.dto.AttendanceDetailDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mario1oreo on 2017/6/27 13:00.
 */

@Mapper
public interface AttendanceDetailDAO {

    @Select("select * from t_attendance_detail")
    List<AttendanceDetailDTO> listAllAttendanceDetail();

    @Insert("insert into t_attendance_detail (EMPLOYEE_ID,ATTENDANCE_DATE,ATTENDANCE_TIME,ATTENDANCE_TYPE,ATTENDANCE_STATUS,IS_HOLIDAY) values(#{employeeId},#{attendanceDate,jdbcType=DATE},#{attendanceTime,jdbcType=TIME},#{attendanceType},#{attendanceStatus},#{isHoliday})")
    void save(AttendanceDetailDTO attendanceDetailDTO);

    @Select("select count(1) from t_attendance_detail")
    Integer countAll();
}
