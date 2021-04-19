package com.shani.service;

import com.shani.model.Research;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Research")
public class ResearchService
{
Research Obj = new Research();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readResearch()
{
return Obj.readResearch();
}



@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertItem(@FormParam("name") String name,
 @FormParam("email") String email,
 @FormParam("number") String number,
 @FormParam("projname") String projname,
 @FormParam("details") String details
)
{
 String output = Obj.insertResearch(name, email, number, projname, details);
return output;
}




@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateResearch(String researchdata)
{
//Convert the input string to a JSON object
 JsonObject Object = new JsonParser().parse(researchdata).getAsJsonObject();
//Read the values from the JSON object
 String rID = Object.get("rID").getAsString();
 String name = Object.get("name").getAsString();
 String email = Object.get("email").getAsString();
 String number = Object.get("number").getAsString();
 String projname = Object.get("projname").getAsString();
 String details = Object.get("details").getAsString();
 
 String output = Obj.updateResearch(rID, name, email, number, projname, details);
return output;
}





@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteRsearch(String researchdata)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(researchdata, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String rID = doc.select("rID").text();
 String output = Obj.deleteResearch(rID);
return output;
}




}

 
