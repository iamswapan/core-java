package com.demo;

public class Employee {
	
	private String name;
	private Integer age;
	private String profession;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", profession=" + profession + "]";
	}
	
	

}
