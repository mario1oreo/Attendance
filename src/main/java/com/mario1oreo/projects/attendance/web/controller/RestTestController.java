package com.mario1oreo.projects.attendance.web.controller;

import com.mario1oreo.projects.attendance.dto.AttendanceDetailDTO;
import com.mario1oreo.projects.attendance.dto.DayDetailDTO;
import com.mario1oreo.projects.attendance.dto.ReportDetailDTO;
import com.mario1oreo.projects.attendance.service.AttendanceDetailService;
import com.mario1oreo.projects.attendance.service.ShowReportService;
import com.mario1oreo.projects.attendance.util.file.ExcelHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
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

    @Autowired
    private ShowReportService showReportServiceImpl;

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
        String fileName = "2017-9/9.xlsx";
        try {
            long t1 = System.currentTimeMillis();
            List<DayDetailDTO> result = ExcelHelper.readExcel(fileName);
            long t2 = System.currentTimeMillis();
            log.info("read Excel finish! cost:{}s",(t2-t1)/1000);
            attendanceDetailServiceImpl.saveAttendanceDetailByDayList(result);
            Integer count = attendanceDetailServiceImpl.countAll();
            return "数据导入成功！共计:" + count;
        } catch (IOException e) {
            log.info("saveAttendanceDetailExcel IOException:{}",e.toString());
            return e.getMessage();
        } finally {
            log.info("exit saveAttendanceDetailExcel!");
        }
    }

    @RequestMapping("/showExceprtionAttendanceDetail")
    @ResponseBody
    public List<ReportDetailDTO> showExceprtionAttendanceDetail(@RequestParam(defaultValue = "") String startDate,@RequestParam(defaultValue = "") String endDate,@RequestParam(defaultValue = "false") boolean last) {
        log.info("entry showExceprtionAttendanceDetail!");

        List<ReportDetailDTO> result = showReportServiceImpl.listExceptionAttendance(startDate, endDate,last);
        log.info("异常记录共计：{}",result.size());
        log.info("exit showExceprtionAttendanceDetail!");
        return result;
    }

    @RequestMapping("/listTotalReport")
    @ResponseBody
    public List<ReportDetailDTO> listTotalReport(@RequestParam(defaultValue = "") String startDate,@RequestParam(defaultValue = "") String endDate,@RequestParam(defaultValue = "false") boolean last) {
        log.info("entry listTotalReport!");

        List<ReportDetailDTO> result = showReportServiceImpl.listTotalReport(startDate, endDate,last);
        log.info("异常记录共计：{}",result.size());
        log.info("exit listTotalReport!");
        return result;
    }

}
