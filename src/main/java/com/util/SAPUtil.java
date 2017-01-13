package com.util;

import java.util.HashMap;
import java.util.Map;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoMetaData;
import com.sap.conn.jco.JCoParameterList;

public class SAPUtil {

	public Map<String, String> execute(String method, Map<String, String> paramIn){
		Map<String, String> res = new HashMap<>();
		JCoFunction function = null;  
        JCoDestination destination = SAPConn.connect();  
        String result="";//调用接口返回状态  
        String message="";//调用接口返回信息  
        try {  
            //调用ZRFC_GET_REMAIN_SUM函数  
            function = destination.getRepository().getFunction(method);  
            JCoParameterList input = function.getImportParameterList();  
            if(input != null && paramIn != null){
            	int icount = input.getMetaData().getFieldCount();
            	for(int i = 0; i < icount; i++){
            		String fieldName = input.getMetaData().getName(i);
            		if(paramIn.containsKey(fieldName)){
            			input.setValue(fieldName, paramIn.get(fieldName));
            		}
            	}
            }
            //获取传入表参数SN_ITEM  
            JCoParameterList SN_ITEM = function.getTableParameterList();
            function.execute(destination); 
            JCoMetaData outMetaData = function.getExportParameterList().getMetaData();
            int ocount = outMetaData.getFieldCount();
            for(int i = 0 ; i < ocount; i++){
            	String name = outMetaData.getName(i);
            	String value = function.getExportParameterList().getString(name);
            	res.put(name, value);
            }
           
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        return res;
	}
}
