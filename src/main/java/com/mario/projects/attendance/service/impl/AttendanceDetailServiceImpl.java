package com.mario.projects.attendance.service.impl;

import com.mario.projects.attendance.dao.AttendanceDetailDAO;
import com.mario.projects.attendance.dao.EmployeeInfoDAO;
import com.mario.projects.attendance.dto.AttendanceDetailDTO;
import com.mario.projects.attendance.dto.DayDetailDTO;
import com.mario.projects.attendance.dto.EmployeeInfoDTO;
import com.mario.projects.attendance.service.AttendanceDetailService;
import com.mario.projects.attendance.util.translate.DtoTranslateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mario1oreo on 2017/6/29 0:15.
 */
@Service
@Slf4j
public class AttendanceDetailServiceImpl implements AttendanceDetailService {

    @Autowired
    private AttendanceDetailDAO attendanceDetailDAO;

    @Autowired
    private EmployeeInfoDAO employeeInfoDAO;

    @Override
    public void saveAttendanceDetailByDayList(List<DayDetailDTO> list) {
        //1.translate List<DayDetailDTO> to List<AttendanceDetailDTO>
        List<AttendanceDetailDTO> targetList = new ArrayList<>();
        List<EmployeeInfoDTO> employeeInfoList = new ArrayList<>();
        log.info("DtoTranslateHelper.dayDetailToAttenDetail begin!");
        long t1 = System.currentTimeMillis();
        Set<String> employeeId = employeeInfoDAO.listEmployeeId();
        for (DayDetailDTO dayDetailDTO : list) {
            targetList.addAll(DtoTranslateHelper.dayDetailToAttenDetail(dayDetailDTO));
            if (!employeeId.contains(dayDetailDTO.getEmployeeId())) {
                employeeId.add(dayDetailDTO.getEmployeeId());
                employeeInfoList.add(DtoTranslateHelper.dayDatailTOEmployeeInfo(dayDetailDTO));
            }
        }
        long t2 = System.currentTimeMillis();
        log.info("DtoTranslateHelper.dayDetailToAttenDetail!  cost:{}", t2 - t1);

        if (targetList.size() == 0) {
            log.error("saveAttendanceDetailByDayList 转换结果为0，没有生成任何AttendanceDetailDTO,不能插入数据！");
            return;
        }
        log.info("translate list.size:{}", targetList.size());


        log.info("attendanceDetailDAO.save  begin!");
        t1 = System.currentTimeMillis();
        //2.foreach and save AttendanceDetailDTO
        for (AttendanceDetailDTO attendanceDetailDTO : targetList) {
            attendanceDetailDAO.save(attendanceDetailDTO);
        }
        t2 = System.currentTimeMillis();
        log.info("attendanceDetailDAO.save!  cost:{}", t2 - t1);

        if (employeeInfoList.size() == 0) {
            log.info("saveEmployeeInfoList 转换结果为0，没有生成任何EmployeeInfoDTO,不插入数据！");
        } else {
            log.info("employeeInfoList list.size:{}", targetList.size());
            log.info("employeeInfoDAO.save  begin!");
            t1 = System.currentTimeMillis();
            //3.foreach and save EmployeeInfo
            for (EmployeeInfoDTO employeeInfoDTO : employeeInfoList) {
                employeeInfoDAO.save(employeeInfoDTO);
            }
            t2 = System.currentTimeMillis();
            log.info("employeeInfoDAO.save!  cost:{}", t2 - t1);
        }

    }

    @Override
    public void saveAttendanceDetail(List<AttendanceDetailDTO> list) {
        //foreach and save AtendanceDetailDTO
        for (AttendanceDetailDTO attendanceDetailDTO : list) {
            attendanceDetailDAO.save(attendanceDetailDTO);
        }
    }

    @Override
    public void saveAttendanceDetail(AttendanceDetailDTO dto) {
        //save AttendanceDetailDTO
        attendanceDetailDAO.save(dto);
    }

    @Override
    public List<AttendanceDetailDTO> listAll() {
        return attendanceDetailDAO.listAllAttendanceDetail();
    }

    @Override
    public Integer countAll() {
        return attendanceDetailDAO.countAll();
    }
}
