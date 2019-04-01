package com.test.springtest.test;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/3/29
 */
public class TestExcelRead {

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream in = new FileInputStream("/Users/xjune/Documents/workspace/springtest/src/main/java/com/test/springtest/test/FIN_20180329102738.xlsx");
        Workbook wk = StreamingReader.builder()
                .rowCacheSize(100)  //缓存到内存中的行数，默认是10
                .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                .open(in);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
        Sheet sheet = wk.getSheetAt(0);
        int i = 0;
        //遍历所有的行
        System.out.println("开始遍历");

        for (Row row : sheet) {
            //System.out.println(i%100);
            if(i>10000&&i%10000==0) {
                System.out.println("开始遍历第" + row.getRowNum() + "行数据：");
                //遍历所有的列
                for (Cell cell : row) {
                    System.out.print(cell.getStringCellValue() + " ");
                }
            }
            i++;
            //System.out.println(" ");
        }
        System.out.println("total:"+ i);

    }



}
