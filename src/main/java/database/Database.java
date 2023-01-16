package database;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
	private ArrayList<Student> students;
	
	public Database() {
		students = new ArrayList<Student>();
	}
	
	public String connectDB(String sql) {
		if (sql.startsWith("select")) {
			return selectDB(sql);
		}
		else if (sql.startsWith("insert")) {
			return insertDB(sql);
		}
		else if (sql.startsWith("update")) {
			return updateDB(sql);
		}
		else if (sql.startsWith("delete")) {
			return deleteDB(sql);
		}
		else {
			throw new DBCommandException();
		}
	}
	
	public String selectDB(String sql) { //select ?=?;
		String[] params = sql.split(" ");
		String result = "";
		
		if (params.length == 1) {
			for (Student s : students) {
				result += s.getInfo();
				result += "\n";
			}
		} else if (params.length == 2) {
			params = params[1].split("=");
			for (Student s : students) {
				if (checkValue(s, params)) {
					result += s.getInfo();
					result += "\n";
				}
			}
		} else {
			throw new DBCommandException();
		}
		return result;
	}
	
	public String insertDB(String sql) { //insert (이름,학번,단과대,학과)
		String[] params = ((sql.split(" "))[1]).substring(1,((sql.split(" "))[1]).length() - 1).split(",");
		students.add(new Student(params[0],Integer.parseInt(params[1]),params[2],params[3]));
		return "true";
	}
	
	public String updateDB(String sql) { //update ?=? where ?=?
		String[] params = sql.split(" ");
		boolean check = false;

		if (params.length == 4) {
			String[] params1, params2;
			params1 = params[1].split("=");
			params2 = params[3].split("=");
			
			for (Student s : students) {
				if (checkValue(s,params2)) {
					setValue(s,params1);
					check = true;
				}
			}
		}
		if (check) return "true";
		return "false";
	}
	
	public String deleteDB(String sql) { //delete ?=?
		String[] params = sql.split(" ");
		boolean check = false;
		
		if (params.length == 2) {
			params = params[1].split("=");
			if (params[0].equals("name")) {
				for (Student s : students) {
					if (s.getName().equals(params[1])) {
						students.remove(s);
						check = true;
					}
				}
			}
			else if (params[0].equals("studNum")) {
				for (Student s : students) {
					if (s.getStudNum() == Integer.parseInt(params[1])) {
						students.remove(s);
						check = true;
					}
				}
			}
			else if (params[0].equals("college")) {
				for (Student s : students) {
					if (s.getCollege().equals(params[1])) {
						students.remove(s);
						check = true;
					}
				}
			}
			else if (params[0].equals("department")) {
				for (Student s : students) {
					if (s.getDepartment().equals(params[1])) {
						students.remove(s);
						check = true;
					}
				}
			}
		}
		if (check) return "true";
		return "false";
	}
	
	public boolean checkValue(Student student, String[] params) {
		if (params[0].equals("name")) {
			if (student.getName().equals(params[1])) {
				return true;
			}
		}
		else if (params[0].equals("studNum")) {
			if (student.getStudNum() == Integer.parseInt(params[1])) {
				return true;
			}
		}
		else if (params[0].equals("college")) {
			if (student.getCollege().equals(params[1])) {
				return true;
			}
		}
		else if (params[0].equals("department")) {
			if (student.getDepartment().equals(params[1])) {
				return true;
			}
		}
		return false;
	}
	
	public void setValue(Student student, String[] params) {
		if (params[0].equals("name")) {
			student.setName(params[1]);
		}
		else if (params[0].equals("studNum")) {
			student.setStudNum(Integer.parseInt(params[1]));
		}
		else if (params[0].equals("college")) {
			student.setCollege(params[1]);
		}
		else if (params[0].equals("department")) {
			student.setDepartment(params[1]);
		}
	}
}

