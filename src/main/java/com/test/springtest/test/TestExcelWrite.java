package com.test.springtest.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/3/29
 */
public class TestExcelWrite {

    public static void main(String[] args) throws IOException {
        // create a new file
        //FileOutputStream out = new FileOutputStream("workbook.xls");
        Workbook wb = new HSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("/Users/xjune/Documents/workspace/springtest/src/main/java/com/test/springtest/test/workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }


}
