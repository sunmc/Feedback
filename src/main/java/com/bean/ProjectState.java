package com.bean;

public interface ProjectState {
	
	public final static String schemaCode = "XMWTGL";
	
	//提出
	public static final String WTTC = "WTTC";
	
	//判定
	public static final String WTPD = "WTPD";
	
	//分析
	public static final String WTFX = "WTFX";
	
	//分派
	public static final String WTFP = "WTFP";
	
	//整改
	public static final String WTZG = "WTZG";

	//解决确认
	public static final String WTQR = "WTQR";
	
	//关闭
	public static final String WTGB = "WTGB";
	
	
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
	
}
