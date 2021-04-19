package com.shani.model;

import java.sql.*;

public class Research {

	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	
	
	public String insertResearch(String name, String email, String number, String projname, String details)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into research (`rID`,`name`,`email`,`number`,`projname`,`details`)"
	 + " values (?, ?, ?, ?,?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, email);
	 preparedStmt.setString(4, number);
	 preparedStmt.setString(5, projname);
	 preparedStmt.setString(6, details);
	// execute the statement
	
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
	
	
	
	
	
	
	
	public String readResearch()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr> <th>Research ID</th>" + 
	 "<th>Name</th>" +
	 "<th>Email</th>" +
	 "<th>Number</th>"+
	 "<th>Project Name</th>" + 
	 "<th>Details</th>" + 
	 "<th>Update</th>" + 
	 "<th>Remove</th>" + 
	 "</tr>";

	 String query = "select * from research";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String rID = rs.getString("rID");
	 String name = rs.getString("name");
	 String email = rs.getString("email");
	 String number = rs.getString("number");
	 String projname = rs.getString("projname");
	 String details = rs.getString("details");
	 // Add into the html table
	 output += "<tr><td>" + rID+ "</td>";
	 output += "<td>" + name + "</td>";
	 output += "<td>" + email + "</td>";
	 output += "<td>" + number + "</td>";
	 output += "<td>" + projname + "</td>";
	 output += "<td>" + details+ "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> </td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='rID' type='hidden' value='" + rID
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
	
	
	
	
	public String updateResearch(String rID, String name, String email, String number , String projname , String details)
	
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE research SET name=?,email=?,number=?,projname=?,details=? WHERE rID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, name);
	 preparedStmt.setString(2, email);
	 preparedStmt.setString(3, number);
	 preparedStmt.setString(4, projname);
	 preparedStmt.setString(5, details);
	 preparedStmt.setString(6, rID);
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
	
	
	
	public String deleteResearch(String rID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from research where rID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setString(1,rID);
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
