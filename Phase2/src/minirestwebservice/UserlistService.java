package minirestwebservice;



import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import userlist.ObjectFactory;
import userlist.User;
import userlist.Userlist;

@Path( "/users" )
public class UserlistService
{
	
   @GET 
   @Produces( MediaType.APPLICATION_XML )
   public Userlist getAllUsers() throws Exception
   {
	   ObjectFactory ob = new ObjectFactory();
	   Userlist users=ob.createUserlist();
	   JAXBContext jc = JAXBContext.newInstance(Userlist.class);
		//unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    users = (Userlist) um.unmarshal(new FileInputStream("XML/Userlist.xml"));
	    
	    
      return users; 
   }
   
   @GET 
   @Path( "/{userID}" )
   @Produces( MediaType.APPLICATION_XML )
   public Response getOneUser(@PathParam("userID") int i) throws Exception
   {
	   
	    JAXBContext jc = JAXBContext.newInstance(Userlist.class);
		//unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    
	    Userlist users = (Userlist) um.unmarshal(new FileInputStream("XML/Userlist.xml"));
	    
	    
      return Response.status(200).entity(users.getUser().get(i-1)).build() ; //Gibt Meldung 200->"ok" zurück
   }
   
   @POST 
   @Consumes( MediaType.APPLICATION_XML )
   public Response postNewUser( User user ) throws Exception
   {
	   
	    JAXBContext jc = JAXBContext.newInstance(Userlist.class);
	    //unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    //marshaller zum schreiben
	    Marshaller marshaller =jc.createMarshaller();
	    
	    Userlist users = (Userlist) um.unmarshal(new FileInputStream("XML/Userlist.xml"));
	    
	    List<User> usersliste = users.getUser();
	    
	    user.setUserID( new BigInteger(String.valueOf(usersliste.size() - 1)) );
	    
	 	usersliste.add( user );
	    
	    marshaller.marshal(users, new File("XML/Userlist.xml"));
	    
	    
      URI location = URI.create( "http://localhost:4434/users/" + user.getUserID().toString() );
      return Response.created(location ).build(); 
   }
   
   @PUT
   @Path( "/{userID}" )
   @Consumes( MediaType.APPLICATION_XML )
   public Response changeUser( @PathParam("userID") int id, User user  ) throws Exception
   {
	   
	    JAXBContext jc = JAXBContext.newInstance(Userlist.class);
	    //unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    //marshaller zum schreiben
	    Marshaller marshaller =jc.createMarshaller();
	    
	    Userlist users = (Userlist) um.unmarshal(new FileInputStream("XML/userlist.xml"));
	    
	    
	    List<User> usersliste = users.getUser();
	    
	    int i=0;;
	    for(User us : usersliste ){
	    	if(us.getUserID().equals(id)){
	    		usersliste.set(i, user);
	    	}
	    	i++;
	    }

	    marshaller.marshal(users, new File("XML/Userlist.xml"));
	    
	    
      URI location = URI.create( "http://localhost:4434/users/" + user.getUserID().toString() );
      return Response.created(location ).build();  
   }
   
   @DELETE
   @Path( "/{userID}" )
   public Response deleteUser( @PathParam("userID") int id  ) throws Exception
   {
	   
	    JAXBContext jc = JAXBContext.newInstance(Userlist.class);
	    //unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    //marshaller zum schreiben
	    Marshaller marshaller =jc.createMarshaller();
	    
	    Userlist users = (Userlist) um.unmarshal(new FileInputStream("XML/Userlist.xml"));
	    
	    
	    List<User> usersliste = users.getUser();
	    
	    int i=0;
	    for(User us : usersliste ){
	    	if(us.getUserID().equals(id)){
	    		usersliste.remove(i);
	    	}
	    	i++;
	    }

	    marshaller.marshal(users, new File("XML/Userlist.xml"));
	    
	    
      return Response.noContent().build() ; //Gibt Meldung 204->"ok" zurück
   }
   
}