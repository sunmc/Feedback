package com.qq.weixin.mp.aes.bean;

import java.util.List;

public class DepartmentList {
	public String errcode;//	返回码
	public String errmsg;//	对返回码的文本描述内容
	public List<Department> department; //部门列表数据。以部门的order字段从小到大排列
}
