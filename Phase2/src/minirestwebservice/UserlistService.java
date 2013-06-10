package minirestwebservice;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import userlist.User;
import userlist.Userlist;

@Path( "/users" )
public class UserlistService
{
	
   @GET 
   @Produces( MediaType.APPLICATION_XML )
   public Response getAllUsers(		@QueryParam("name") String name,
		   							@QueryParam("land") String land ) throws Exception
   {
	    
	    DataHandlerUser handle = new DataHandlerUser();	    
	    Userlist userliste = (Userlist) handle.getUsers();
	    List<User> users = userliste.getUser();
	    
	    if(name!=null){
	    	for (Iterator<User> iter = users.iterator(); iter.hasNext(); ) {
		    	User us = iter.next();
		    	if(!us.getUsername().toLowerCase().startsWith(name.toLowerCase())){
			    	iter.remove();
		    	}
		    }
	    }
	    
	    if(land!=null){
	    	for (Iterator<User> iter = users.iterator(); iter.hasNext(); ) {
		    	User us = iter.next();
		    	if(!us.getLand().toString().toLowerCase().startsWith(land.toLowerCase())){
			    	iter.remove();
		    	}
		    }
	    }
	    
	   return Response.status(200).entity(userliste).build() ; //Gibt Meldung 200->"ok" zurück
   }
   
   @GET 
   @Path( "/{userID}" )
   @Produces( MediaType.APPLICATION_XML )
   public Response getOneUser(@PathParam("userID") int i) throws Exception
   {
	   
	    DataHandlerUser handle = new DataHandlerUser();	    
      return Response.status(200).entity(handle.getUserbyID(i)).build() ; //Gibt Meldung 200->"ok" zurück
   }
   
   @POST 
   @Consumes( MediaType.APPLICATION_XML )
   public Response postNewUser( User user ) throws Exception
   {
	    
	    DataHandlerUser handle = new DataHandlerUser();	    
	    Userlist users = (Userlist) handle.getUsers();
	    List<User> userliste = users.getUser();
	    
	    
	    BigInteger id = BigInteger.ZERO ;
		for(User us : userliste ){
			
	    	if(us.getUserID().compareTo(id)==1){
	    		id = us.getUserID();
	    	}
	    }
		user.setUserID(id.add(BigInteger.ONE));
		
	    return Response.created(handle.writeNewUser(user) ).build(); 
   }
   
   @PUT
   @Path( "/{userID}" )
   @Consumes( MediaType.APPLICATION_XML )
   public Response changeUser( @PathParam("userID") int id, User user  ) throws Exception
   {
	   DataHandlerUser handle = new DataHandlerUser();	    
      
	   return Response.created(handle.writeUser(user,id) ).build();  
   }
   
   @DELETE
   @Path( "/{userID}" )
   public Response deleteUser( @PathParam("userID") int id  ) throws Exception
   {
	   
	   DataHandlerUser handle = new DataHandlerUser();	      
	   handle.delete(id);
	    
	   return Response.noContent().build() ; //Gibt Meldung 204->"ok" zurück
   }
   
}