package com.mario1oreo.projects.attendance.dao;

import com.mario1oreo.projects.attendance.dto.EmployeeInfoDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * Created by mario1oreo on 2017/7/16 22:05.
 */
@Mapper
public interface EmployeeInfoDAO {

    @Select("select distinct(EMPLOYEE_ID) from t_employee_info")
    Set<String> listEmployeeId();

    @Insert("INSERT INTO attendance.t_employee_info (EMPLOYEE_ID, NAME, DEPARTMENT) VALUES (#{employeeId},#{name},#{department})")
    void save(EmployeeInfoDTO employeeInfoDTO);
}
