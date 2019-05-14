package com.springioc.entity;
/**
 * 作者：administrator
 * 时间：2019年5月14日 下午9:25:45
 * 说明：
 */
public class User {
	private String name;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", sex=" + sex + "]";
	}
}
