package controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;


import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import database.Database;

@Controller
public class FindStudController {
	private ObjectMapper objectMapper = new ObjectMapper();
	private Database database;
	
	public void setDatabase(Database database) {
		this.database = database;
	}
	
	@ResponseBody
	@RequestMapping(value="/find",method=RequestMethod.POST)
	public String findStud_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");  
		ServletInputStream inputStream = request.getInputStream();
		 
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		StudJSON studJSON = objectMapper.readValue(messageBody, StudJSON.class);
	        
        PrintWriter out = response.getWriter();
        JSONObject resMap = new JSONObject();
        
        String answer = database.connectDB("select studNum="+studJSON.getStudNum());

        if (!answer.equals("")) {
        	 resMap.put("res", "ok");
             resMap.put("answer", answer);
        }
        else {
        	resMap.put("res", "no");
        }
        out.print(resMap);
        return null;
    }
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String deleteStud_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");  
		ServletInputStream inputStream = request.getInputStream();
		 
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		StudJSON studJSON = objectMapper.readValue(messageBody, StudJSON.class);
	        
        PrintWriter out = response.getWriter();
        JSONObject resMap = new JSONObject();
        
        String answer = database.connectDB("delete studNum="+studJSON.getStudNum());

        resMap.put("res", "ok");
        
        if (answer.equals("true"))
        	resMap.put("res", "ok");
        else
        	resMap.put("res", "no");

        out.print(resMap);
        return null;
    }
	
	@ResponseBody
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insertStud_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");  
		ServletInputStream inputStream = request.getInputStream();
		 
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		StudJSON insertStudJSON = objectMapper.readValue(messageBody, StudJSON.class);
	        
        PrintWriter out = response.getWriter();
        JSONObject resMap = new JSONObject();
        
        String answer = database.connectDB("insert (" + insertStudJSON.getName() + "," + insertStudJSON.getStudNum() + "," + insertStudJSON.getCollege() + "," + insertStudJSON.getDepartment() + ")");

        if (answer.equals("true"))
        	resMap.put("res", "ok");
        else
        	resMap.put("res", "no");

        out.print(resMap);
        return null;
    }
	
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateStud_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");  
		ServletInputStream inputStream = request.getInputStream();
		 
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		StudJSON insertStudJSON = objectMapper.readValue(messageBody, StudJSON.class);
	        
        PrintWriter out = response.getWriter();
        JSONObject resMap = new JSONObject();
        
        String target = "";

        
        if (insertStudJSON.getName() != "null") {
        	target = "name="+insertStudJSON.getName();
        }
        else if (insertStudJSON.getCollege() != "null") {
        	target = "college="+insertStudJSON.getCollege();
		}
        else if (insertStudJSON.getDepartment() != "null") {
        	target = "department="+insertStudJSON.getDepartment();
		}
        
        String answer = database.connectDB("update " + target + " where studNum=" + insertStudJSON.getStudNum());
        
        if (answer.equals("true"))
        	resMap.put("res", "ok");
        else
        	resMap.put("res", "no");

        out.print(resMap);
        return null;
    }
	
	public static class StudJSON {
		private String name;
		private String studNum;
		private String college;
		private String department;
		
		public StudJSON() {}
		
		public void setName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setStudNum(String studNum) {
			this.studNum = studNum;
		}
		public String getStudNum() {
			return studNum;
		}
		public void setCollege(String college) {
			this.college = college;
		}
		public String getCollege() {
			return college;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getDepartment() {
			return department;
		}
	}
}
