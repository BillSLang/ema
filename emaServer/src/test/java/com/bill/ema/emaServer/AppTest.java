package com.bill.ema.emaServer;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	public static void main(String[] args)throws IOException{
		Workbook wk = new HSSFWorkbook();//创建一个工作簿
		Sheet sh = wk.createSheet("第一个sheet页");//创建一个工作簿
		Row row = sh.createRow(0);//创建第一行
		Cell cell = row.createCell(0);//创建第一行的第一个单元格
		cell.setCellValue(1);//为第一行第一个单元格塞值
		row.createCell(1).setCellValue(1.2);//创建第一行第2个单元格并赋值
		row.createCell(2).setCellValue("这是一个字符串");//创建第一行第3个单元格并赋值
		row.createCell(3).setCellValue(true);//创建第一回第4个单元格并赋值
		FileOutputStream out = new FileOutputStream("D:\\cellsAndSheet页.xls");
		wk.write(out);
		out.close();
		
		wk.write(out);
		out.close();
		
	}
	
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
