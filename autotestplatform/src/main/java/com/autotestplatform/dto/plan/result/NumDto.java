package com.autotestplatform.dto.plan.result;

import java.io.Serializable;

public class NumDto implements Serializable{
    /** 
    * @Fields serialVersionUID  
    */
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer num1;
    private Integer num2;
    private Integer num3;
    private Integer num4;
    private double double1;
    private double double2;
    private String str1;
    private String str2;
	public NumDto(Integer id, Integer num1, Integer num2, Integer num3, Integer num4, double double1, double double2,
			String str1, String str2) {
		super();
		this.id = id;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.num4 = num4;
		this.double1 = double1;
		this.double2 = double2;
		this.str1 = str1;
		this.str2 = str2;
	}
	public NumDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNum1() {
		return num1;
	}
	public void setNum1(Integer num1) {
		this.num1 = num1;
	}
	public Integer getNum2() {
		return num2;
	}
	public void setNum2(Integer num2) {
		this.num2 = num2;
	}
	public Integer getNum3() {
		return num3;
	}
	public void setNum3(Integer num3) {
		this.num3 = num3;
	}
	public Integer getNum4() {
		return num4;
	}
	public void setNum4(Integer num4) {
		this.num4 = num4;
	}
	public double getDouble1() {
		return double1;
	}
	public void setDouble1(double double1) {
		this.double1 = double1;
	}
	public double getDouble2() {
		return double2;
	}
	public void setDouble2(double double2) {
		this.double2 = double2;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
    
    
	

}
