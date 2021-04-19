package com.gayara.service;

import com.gayara.model.Register;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType; 
import com.google.gson.*; 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 


@Path("/register")
public class RegisterService {
	Register ob=new Register();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDetails()
	{
		return ob.readDetails();
	}
	
	
	
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertuserdetails(@FormParam("Name") String Name,
	 @FormParam("Email") String Email,
	 @FormParam("Address") String Address,
	 @FormParam("Username") String Username,
	 @FormParam("Password") String Password)
	{
	 String output = ob.insertuserdetails(Name, Email, Address, Username,Password);
	return output;
	}
	
	
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDetails(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject object = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String ID = object.get("ID").getAsString();
	 String Name = object.get("Name").getAsString();
	 String Email = object.get("Email").getAsString();
	 String Address = object.get("Address").getAsString();
	 String Username = object.get("Username").getAsString();
	 String Password = object.get("Password").getAsString();
	 String output = ob.updateDetails(ID, Name, Email, Address, Username,Password);
	return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDetails(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String ID = doc.select("ID").text();
	 String output = ob.deleteDetails(ID);
	return output;
	}

	

}
