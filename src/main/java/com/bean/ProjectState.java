package com.bean;

public interface ProjectState {
	
	public final static String schemaCode = "XMWTGL";
	
	public static final String WTTC = "WTTC";
	public static final String WTPD = "WTPD";
	public static final String WTFX = "WTFX";
	public static final String WTJJ = "WTJJ";
	public static final String WTGB = "WTGB";
	
	public static final int START = 1;
	
	public static final int DOING = 2;
	
	public static final int DOWN = 3;
	
	public static final int FREEZE = 4;
	
	public static final int CANCEL = 5;
	
}
