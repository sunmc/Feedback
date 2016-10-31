package com.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.util.bean.Result;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelUtil {
	public Result<String> readJsonFromExcel(String filename){
		Result<String> res = new Result<>();
		List<String> names = new ArrayList<>();
		try{
			Workbook rwb=Workbook.getWorkbook(new File(filename));
	        Sheet rs=rwb.getSheet("Sheet1");//或者rwb.getSheet(0)
	        int clos=rs.getColumns();//得到所有的列
	        int rows=rs.getRows();//得到所有的行
	        //获取字段名
	        for(int j = 0; j < clos; j++){
	        	String name = rs.getCell(j, 0).getContents();;
	        	names.add(name);
	        }
	        String data = "[";
	        for (int i = 2; i < rows; i++) {
	        	data += "{";
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String value=rs.getCell(j, i).getContents();
                    String temp = "\""+names.get(j)+"\":"+"\""+value+"\",";
                    if(j == clos-1){
                    	temp = "\""+names.get(j)+"\":"+"\""+value+"\"";
                    }
                    data += temp;
                }
                if(i == rows-1){
                	data += "}]";
                }else{
                	data += "},";
                }
            }
	        res.setData(data);
	        res.setFlag(true);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}
}
