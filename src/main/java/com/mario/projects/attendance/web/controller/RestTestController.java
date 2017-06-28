package com.mario.projects.attendance.web.controller;

import com.mario.projects.attendance.dao.AttendanceDetailDAO;
import com.mario.projects.attendance.dto.AttendanceDetailDTO;
import com.mario.projects.attendance.dto.DayDetailDTO;
import com.mario.projects.attendance.service.AttendanceDetailService;
import com.mario.projects.attendance.util.file.ExcelHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by mario1oreo on 2017/6/27 13:04.
 */
@RestController
@RequestMapping("/rest")
@Slf4j
public class RestTestController {


    @Autowired
    private AttendanceDetailService attendanceDetailServiceImpl;

    @RequestMapping("/atten")
    @ResponseBody
    public String atten() {
        log.info("entry atten!");
        List<AttendanceDetailDTO> detailList = attendanceDetailServiceImpl.listAll();
        StringBuilder sb = new StringBuilder();
        for (AttendanceDetailDTO attendanceDetailDTO : detailList) {
            sb.append(attendanceDetailDTO.toString()).append("\n");
            log.info(attendanceDetailDTO.toString());
        }
        return sb.toString();
    }

    @RequestMapping("/saveAttendanceDetailExcel")
    @ResponseBody
    public String saveAttendanceDetailExcel() {
        log.info("entry saveAttendanceDetailExcel!");
        String fileName = "2017-6/20170627.xls";
        try {
            List<DayDetailDTO> result = ExcelHelper.readExcel(fileName);
            attendanceDetailServiceImpl.saveAttendanceDetailByDayList(result);
            Integer count = attendanceDetailServiceImpl.countAll();
            return "数据导入成功！共计:" + count;
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            log.info("exit saveAttendanceDetailExcel!");
        }
    }


}
