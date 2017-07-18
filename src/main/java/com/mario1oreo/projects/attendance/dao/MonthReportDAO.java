package com.mario1oreo.projects.attendance.dao;

import com.mario1oreo.projects.attendance.dto.ReportDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mario1oreo on 2017/7/17 0:20.
 */
@Mapper
public interface MonthReportDAO {

    @Select("SELECT t2.DEPARTMENT 'department',t2.NAME 'name',t1.ATTENDANCE_DATE 'date', CASE t1.ATTENDANCE_TYPE WHEN '0' THEN '上午' WHEN '1' THEN '下午' END 'noon', CASE t1.ATTENDANCE_STATUS WHEN '3' THEN '未打卡' WHEN '4' THEN '未打卡' END 'status'\n" +
            "FROM t_attendance_detail t1,t_employee_info t2\n" +
            "WHERE t1.employee_id = t2.EMPLOYEE_ID AND t1.IS_HOLIDAY = 0 AND t1.ATTENDANCE_STATUS IN ('3','4')\n" +
            "and t1.attendance_date between #{startDate} and #{endDate}\n" +
            "ORDER BY t2.DEPARTMENT,t2.NAME,t1.ATTENDANCE_DATE")
    List<ReportDetailDTO> listMonthReport(@Param("startDate") String startDate, @Param("endDate")String endDate);

    @Select("SELECT t2.DEPARTMENT 'department',t2.NAME 'name',CASE t1.ATTENDANCE_TYPE WHEN '0' THEN '上午' WHEN '1' THEN '下午' END 'noon',count(t1.ATTENDANCE_STATUS ) 'times'\n" +
            "FROM t_attendance_detail t1,t_employee_info t2\n" +
            "WHERE t1.employee_id = t2.EMPLOYEE_ID AND t1.IS_HOLIDAY = 0 AND t1.ATTENDANCE_STATUS IN ('3','4')\n" +
            "and t1.attendance_date between #{startDate} and #{endDate}\n" +
            "group by t2.DEPARTMENT,t2.NAME,t1.ATTENDANCE_TYPE ORDER BY t2.DEPARTMENT,t2.NAME")
    List<ReportDetailDTO> listTotalReport(@Param("startDate") String startDate, @Param("endDate")String endDate);
}
