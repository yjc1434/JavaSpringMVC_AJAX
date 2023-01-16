package database;

public class Student {
	private String name;
	private int studNum; //PK
	private String college;
	private String department;
	
	public Student(String name, int studNum, String college, String department) {
		this.name = name;
		this.studNum = studNum;
		this.college = college;
		this.department = department;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getStudNum() {
		return this.studNum;
	}
	
	public void setStudNum(int studNum) {
		this.studNum = studNum;
	}
	
	public String getCollege() {
		return this.college;
	}
	
	public void setCollege(String college) {
		this.college = college;
	}
	
	public String getDepartment() {
		return this.department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getInfo() {
		return "이름: " + this.name + " 학번: " + this.studNum + " 단과대학: " + this.college + " 학과(부): " + this.department;
	}
}
