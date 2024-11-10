package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class Validateuser extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String url="jdbc:mysql://localhost:3306/project1";
		String user="root";
		String pwd="2002";
		String query="Select * from new_table where email=? and password=?";
		PrintWriter pw=res.getWriter();
		
		 try {
		    	Class.forName("com.mysql.cj.jdbc.Driver");
		    	Connection cn=DriverManager.getConnection(url,user,pwd);
		    	PreparedStatement ps= cn.prepareStatement(query);
		    	ps.setString(1, email);
		    	ps.setString(2, password);
		    	ResultSet i=ps.executeQuery();
		    	if(i.next())
		    	{
		    		RequestDispatcher rd= req.getRequestDispatcher("home.html");
		    		rd.include(req, res);
		    		pw.println("Welcome BABu!!");
		    		
		    	}
		    	else
		    	{
		    		RequestDispatcher rd= req.getRequestDispatcher("login.html");
		    		rd.include(req, res);
		    		pw.println("login Failed!!!");
		    		
		    	}
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
	}
}
