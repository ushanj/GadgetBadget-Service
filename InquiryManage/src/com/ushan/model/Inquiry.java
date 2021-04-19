package com.ushan.model;

import java.sql.*;

public class Inquiry {

	
	public static Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget",
	 "root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
		 System.out.print("Not connected");
	 }

	 return con;
	}
	
	
	
	
	public String insertdetails(String name, String email, String subject, String message) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into inquiry  (`eID`,`name`,`email`,`subject`,`message`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, email); 
	 preparedStmt.setString(4, subject); 
	 preparedStmt.setString(5, message); 
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	
	
	
	
	
	public String readInquiry() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Name</th><th>Email</th>" +
	 "<th>Subject</th>" + 
	 "<th>Message</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from inquiry"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String eID = Integer.toString(rs.getInt("eID")); 
	 String name = rs.getString("name"); 
	 String email = rs.getString("email"); 
	 String subject = rs.getString("subject"); 
	 
	 String message = rs.getString("message"); 
	 // Add into the html table
	 output += "<tr><td>" + name + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + subject + "</td>"; 
	 output += "<td>" + message + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='inquiry.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
	 + "<input name='eID' type='hidden' value='" + eID 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	
	
	public String updateinquiry(String eID, String name, String email, String subject, String message)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE inquiry SET name=?,email=?,subject=?,message=?  WHERE eID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, email); 
		 preparedStmt.setString(3, subject); 
		 preparedStmt.setString(4, message); 
		 preparedStmt.setString(5, eID); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 

	
	
	
	public String deleteinquiry(String eID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 
	 // create a prepared statement
	 String query = "delete from inquiry where eID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(eID)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
}
