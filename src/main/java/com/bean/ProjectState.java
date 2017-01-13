package com.bean;

public interface ProjectState {
	
	public final static String schemaCode = "xmwtgl";
	
	//提出
	public static final String WTTC = "wttc";
	
	//判定
	public static final String WTPD = "wtpd";
	
	//处理（分析+整改）
	public static final String WTCL = "wtcl";
	
	//方案执行
	public static final String FAZX = "fazx"; 
	
	//分析
	public static final String WTFX = "wtfx";
	
	//分派
	public static final String WTFP = "wtfp";
	
	//整改
	public static final String WTZG = "wtzg";

	//解决确认
	public static final String WTQR = "wtqr";
	
	//关闭
	public static final String WTGB = "wtgb";
	
	
	//开始
	public static final int START = 1;
	
	//进行中
	public static final int DOING = 2;
	
	//完成
	public static final int DOWN = 3;
	
	//冻结
	public static final int FREEZE = 4;
	
	//取消
	public static final int CANCEL = 5;
	
	//结束
	public static final int END = 6;
	
	public static final int EITHER = 1;
	
	public static final int ALL = 0;
	
}
