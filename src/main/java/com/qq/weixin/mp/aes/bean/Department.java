package com.qq.weixin.mp.aes.bean;

public class Department {
	public String id;//	部门id
	public String name;//	部门名称
	public String parentid;//	父亲部门id。根部门为1
	public String order;//	在父部门中的次序值。order值小的排序靠前。
}
