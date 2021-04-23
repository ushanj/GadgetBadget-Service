package com;
	/**
	 * @author INURI-PC
	 *
	 */
	import model.Cart;
	//For REST Service
	import javax.ws.rs.*;
	import javax.ws.rs.core.MediaType;
	//For JSON
	import com.google.gson.*;
	//For XML
	import org.jsoup.*;
	import org.jsoup.parser.*;
	import org.jsoup.nodes.Document;
	@Path("/Cart")
	public class CartService
	{
	 Cart cartObj = new Cart();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCart()
	 {
	 return cartObj.readCart();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCart(
	 @FormParam("prodid") String prodid,
	 @FormParam("prodname") String prodname,
	 @FormParam("prodqty") String prodqty,
	 @FormParam("prodprice") String prodprice)
	{
	 String output = cartObj.insertCart(prodid, prodname, prodqty, prodprice);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCart(String cartData)
	{
	//Convert the input string to a JSON object
	 JsonObject cartObject = new JsonParser().parse(cartData).getAsJsonObject();
	//Read the values from the JSON object
	 String prodnum = cartObject.get("prodnum").getAsString();
	 String prodid = cartObject.get("prodid").getAsString();
	 String prodname = cartObject.get("prodname").getAsString();
	 String prodqty = cartObject.get("prodqty").getAsString();
	 String prodprice = cartObject.get("prodprice").getAsString();
	 String output = cartObj.updateCart(prodnum, prodid, prodname, prodqty, prodprice);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCart(String cartData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(cartData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String prodnum = doc.select("prodnum").text();
	 String output = cartObj.deleteCart(prodnum);
	return output;
	}

	}

