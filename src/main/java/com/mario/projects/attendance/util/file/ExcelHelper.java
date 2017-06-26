package com.mario.projects.attendance.util.file;

import com.mario.projects.attendance.dto.DayDetailDTO;
import com.mario.projects.attendance.util.date.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mario1oreo on 2017/6/23 3:08.
 */
@Slf4j
public class ExcelHelper {


    /**
     * 判断文件名是不是excel 2003
     *
     * @param filePath
     * @return 是否为2003
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 判断文件名是不是excel 2007
     *
     * @param filePath
     * @return 是否为2007
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return 是否是excel文件
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }

    public static List<DayDetailDTO> readXlsx(String path) throws IOException {
        InputStream is = new FileInputStream(ResourceUtils.getFile("classpath:excel/" + path));
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        DayDetailDTO dayDetailDTO = null;
        List<DayDetailDTO> list = new ArrayList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    dayDetailDTO = new DayDetailDTO();
                    XSSFCell name = xssfRow.getCell(0);
                    System.out.println("name = " + name);
                    XSSFCell department = xssfRow.getCell(1);
                    System.out.println("department = " + department);
                    XSSFCell employeeId = xssfRow.getCell(2);
                    System.out.println("employeeId = " + employeeId);
                    XSSFCell date = xssfRow.getCell(3);
                    System.out.println("date = " + date);
                    XSSFCell isHoliday = xssfRow.getCell(4);
                    System.out.println("isHoliday = " + isHoliday);
                    XSSFCell onAttendanceTime = xssfRow.getCell(5);
                    System.out.println("onAttendanceTime = " + onAttendanceTime);
                    XSSFCell onAttendanceStatus = xssfRow.getCell(6);
                    System.out.println("onAttendanceStatus = " + onAttendanceStatus);
                    XSSFCell offAttendanceTime = xssfRow.getCell(7);
                    System.out.println("offAttendanceTime = " + offAttendanceTime);
                    XSSFCell offAttendanceStatus = xssfRow.getCell(8);
                    System.out.println("offAttendanceStatus = " + offAttendanceStatus);
                    list.add(dayDetailDTO);
                }
            }
        }
        return list;
    }

    public static List<DayDetailDTO> readXls(String path) throws IOException {
        InputStream is = new FileInputStream(ResourceUtils.getFile("classpath:excel/" + path));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        DayDetailDTO dayDetailDTO = null;
        List<DayDetailDTO> list = new ArrayList<>();
        // Read the Sheet
//        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(1);
//            if (hssfSheet == null) {
//                continue;
//            }
        // Read the Row
        for (int rowNum = 4; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            if (hssfRow != null) {
                dayDetailDTO = new DayDetailDTO();
                HSSFCell name = hssfRow.getCell(0);
                System.out.println("name = " + name);

                HSSFCell department = hssfRow.getCell(1);
                System.out.println("department = " + department);

                HSSFCell employeeId = hssfRow.getCell(2);
                System.out.println("employeeId = " + employeeId);

                HSSFCell date = hssfRow.getCell(3);
                System.out.println("date = " + date);

                HSSFCell isHoliday = hssfRow.getCell(4);
                System.out.println("isHoliday = " + isHoliday);

                HSSFCell onAttendanceTime = hssfRow.getCell(5);
                System.out.println("onAttendanceTime = " + onAttendanceTime);

                HSSFCell onAttendanceStatus = hssfRow.getCell(6);
                System.out.println("onAttendanceStatus = " + onAttendanceStatus);

                HSSFCell offAttendanceTime = hssfRow.getCell(7);
                System.out.println("offAttendanceTime = " + offAttendanceTime);

                HSSFCell offAttendanceStatus = hssfRow.getCell(8);
                System.out.println("offAttendanceStatus = " + offAttendanceStatus);

                dayDetailDTO.setName(getValue(name));
                dayDetailDTO.setDepartment(getValue(department));
                dayDetailDTO.setEmployeeId(getValue(employeeId));
                dayDetailDTO.setDate(LocalDate.parse(getValue(date), DateHelper.formatter1));
                dayDetailDTO.setIsHoliday(getValue(isHoliday).startsWith("正常") ? false : true);
                if (StringUtils.isNotEmpty(getValue(onAttendanceTime))) {
                    dayDetailDTO.setOnAttendanceTime(LocalTime.parse(getValue(onAttendanceTime), DateHelper.formatter2));
                }

                dayDetailDTO.setOnStatus(getValue(onAttendanceStatus).contains("正常") ? "0" : getValue(onAttendanceStatus).contains("还未打卡") ? "3" : getValue(onAttendanceStatus).contains("假") ? "1" : "2");
                if (StringUtils.isNotEmpty(getValue(offAttendanceTime))) {
                    dayDetailDTO.setOffAttendanceTime(LocalTime.parse(getValue(offAttendanceTime), DateHelper.formatter2));
                }
                dayDetailDTO.setOffStatus(getValue(offAttendanceStatus).contains("正常") ? "0" : getValue(offAttendanceStatus).contains("还未打卡") ? "3" : getValue(offAttendanceStatus).contains("假") ? "1" : "2");
                System.out.println(dayDetailDTO.toString());
                list.add(dayDetailDTO);
            }
        }
//        }

        return list;
    }

    @SuppressWarnings("static-access")
    private static String getValue(XSSFCell xssfRow) {
        if (xssfRow == null) {
            return "";
        }
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell == null) {
            return "";
        }
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }


    public static void main(String[] args) {
        String fileName = "产品.xls";
        try {
            if (isExcel2007(fileName)) {
                ExcelHelper.readXlsx(fileName);
            } else if ((isExcel2003(fileName))) {
                ExcelHelper.readXls(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
