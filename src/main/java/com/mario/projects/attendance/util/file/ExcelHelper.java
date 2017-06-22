package com.mario.projects.attendance.util.file;

import org.springframework.util.ResourceUtils;

/**
 * Created by mario1oreo on 2017/6/23 3:08.
 */
public class ExcelHelper {


    /**
     * 判断文件名是不是excel 2003
     * @param filePath
     * @return 是否为2003
     */
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 判断文件名是不是excel 2007
     * @param filePath
     * @return 是否为2007
     */
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 验证EXCEL文件
     * @param filePath
     * @return 是否是excel文件
     */
    public static boolean validateExcel(String filePath){
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
//        ResourceUtils.getFile("classpath:")

    }
}
