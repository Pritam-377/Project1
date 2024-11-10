package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 @WebServlet("/register")
public class Registration extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
	    int age=Integer.parseInt(req.getParameter("age"));
		String url="jdbc:mysql://localhost:3306/project1";
		String user="root";
		String pwd="2002";
		String query="insert into new_table values(?,?,?,?)";
		PrintWriter pw=res.getWriter();
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection cn=DriverManager.getConnection(url,user,pwd);
	    	PreparedStatement ps= cn.prepareStatement(query);
	    	ps.setString(1, name);
	    	ps.setString(2, email);
	    	ps.setString(3, password);
	    	ps.setInt(4, age);
	    	int i=ps.executeUpdate();
	    	if(i>0)
	    	{
	    		RequestDispatcher rd= req.getRequestDispatcher("login.html");
	    		rd.include(req, res);
	    		pw.println("Registration Successfull!!");
	    		
	    	}
	    	else
	    	{
	    		RequestDispatcher rd= req.getRequestDispatcher("Registration.html");
	    		rd.include(req, res);
	    		pw.println("Registration Failed!!!");
	    		
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		
	}

}
