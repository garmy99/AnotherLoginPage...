package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	// create the query
	private static final String Insert_Query = "INSERT INTO USER(NAME,CITY,MOBILE,DOB) VALUES(?,?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get printWriter
		     PrintWriter pw = res.getWriter();
		//set content type
		     res.setContentType("text/html");
		     
		     //read the form value

	          String name = req.getParameter("name");
	          String city = req.getParameter("city");
	          String mobile = req.getParameter("mobile");
	          String dob = req.getParameter("dOb");
	       
		      //load the jdbc driver
		    
		     try {
		    	 Class.forName("com.mysql.jdbc.Driver");	 
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		     // create the connection
		     try {
				
		     Connection con = DriverManager.getConnection("jdbc:mysql:///firstDb","root","test");
		          PreparedStatement ps = con.prepareStatement(Insert_Query);{
		    	      // set the values
		    	      ps.setString(1, name);
		    	      ps.setString(2, city);
		    	      ps.setString(3, mobile);
		    	      ps.setString(4, dob);                     	 
		                               	
		    	      //execute the query
		    	      int count= ps.executeUpdate();
		    	      if(count==0) {
		    	    	  pw.println("Record not stored into Database");
		    	      }else {
		    	    	  pw.println("Record stored into Database");
		    	      }
		                               	 
		                   }                               	 
			} catch (SQLException se) {
				pw.println(se.getMessage());
				se.printStackTrace();
			}catch(Exception e) {
				pw.println(e.getMessage());
				e.printStackTrace();
			}
		         
		       // close the stream
		        pw.close(); 
		     
	    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
  }

