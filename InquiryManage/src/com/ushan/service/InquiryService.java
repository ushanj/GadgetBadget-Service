package com.ushan.service;

import com.ushan.model.Inquiry;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/inquiry")
public class InquiryService {

	Inquiry obj = new Inquiry(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return obj.readInquiry(); 
	 } 
	
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertdetails(@FormParam("name") String name, 
	 @FormParam("email") String email, 
	 @FormParam("subject") String subject, 
	 @FormParam("message") String message) 
	{ 
	 String output = obj.insertdetails(name, email, subject, message); 
	return output; 
	}
	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateinquiry(String inquiryData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject ob = new JsonParser().parse(inquiryData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String eID = ob.get("eID").getAsString(); 
	 String name = ob.get("name").getAsString(); 
	 String email = ob.get("email").getAsString(); 
	 String subject = ob.get("subject").getAsString(); 
	 String message = ob.get("message").getAsString(); 
	 String output = obj.updateinquiry(eID, name, email, subject, message); 
	return output; 
	}
	
	
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteinquiry(String inquiryData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String eID = doc.select("eID").text(); 
	 String output = obj.deleteinquiry(eID); 
	return output; 
	}
	
}
