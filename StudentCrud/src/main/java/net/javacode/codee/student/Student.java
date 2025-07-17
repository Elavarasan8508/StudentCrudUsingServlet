package net.javacode.codee.student;

public class Student{
	protected int id;
	protected String name;
	protected int age;
	protected String college;
	
	public Student(int id) {
		this.id = id;
	}
	
	public Student(int id,String name,int age,String college) {
		this(name, age, college);
		this.id = id;
	}
	
	public Student(String name,int age,String college) {
		this.name = name;
		this.age = age;
		this.college = college;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getCollege() {
		return college;
	}
	
	public void setCollege(String college) {
		this.college = college;
	}
}