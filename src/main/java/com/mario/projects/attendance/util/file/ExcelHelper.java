package com.mario.projects.attendance.util.file;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by mario1oreo on 2017/6/23 3:08.
 */
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

    public static void main(String[] args) {
        String fileName = "产品.xls";
        Workbook wk = null;
        try {
            File file = ResourceUtils.getFile("classpath:excel/" + fileName);
            if (isExcel2007(fileName)) {
                wk = new XSSFWorkbook(new FileInputStream(fileName));
            } else if ((isExcel2003(fileName))) {
                wk = new HSSFWorkbook(new FileInputStream(file));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
