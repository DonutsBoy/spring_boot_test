package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.CellStyle; 
import org.apache.poi.ss.usermodel.DataFormat; 
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.ss.usermodel.Sheet; 
import org.apache.poi.ss.usermodel.Workbook; 
import org.springframework.stereotype.Component; 
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

//아래와 같이 컴포넌트를 정의해야 ExcelController 컨트롤러에서 SampleExcel 뷰를 찾을수 있다.
@Component("SampleExcel")

//아래는 xls 파일용
//public class SampleExcelView extends AbstractXlsView {

//아래는 xls, xlsx 파일용 AbstractXlsxView 클래스를 상속받는다.
public class SampleExcelView extends AbstractXlsxView {

    @Override
    //protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String excelName = "샘플엑셀다운로드.xlsx";
    	Sheet worksheet = null; 
    	Row row = null; 
    	worksheet = workbook.createSheet("Sheet1");
    	
    	// 가장 첫번째 줄에 제목을 만든다. 
    	row = worksheet.createRow(0);    	
    	row.createCell(0).setCellValue("ID"); 
    	row.createCell(1).setCellValue("제목"); 
    	row.createCell(2).setCellValue("내용"); 
    	row.createCell(3).setCellValue("작성자"); 
    	
    	response.setHeader("Content-Disposition", "attachment; filename=\"" + java.net.URLEncoder.encode(excelName, "UTF-8") + "\";charset=\"UTF-8\"");
    	
    }

}