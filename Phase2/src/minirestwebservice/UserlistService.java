package minirestwebservice;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import userlist.User;
import userlist.Userlist;

/**
 * Service für User
 * @author Ben
 */

@Path( "/users" )	//Path für Entity
public class UserlistService
{
   //GET auf alle User
   @GET 
   @Produces( MediaType.APPLICATION_XML )
   public Response getAllUsers(		@QueryParam("name") String name,
		   							@QueryParam("land") String land ) throws Exception	//alle User mit QueryParam
   {
	    DataHandlerUser handle = new DataHandlerUser();	    
	    Userlist userliste = (Userlist) handle.getUsers();
	    List<User> users = userliste.getUser();
	    
	    if(name!=null){		//QueryParam realisieren mit Hilfe des Iterator
	    	for (Iterator<User> iter = users.iterator(); iter.hasNext(); ) {
		    	User us = iter.next();
		    	if(!us.getUsername().toLowerCase().startsWith(name.toLowerCase())){
			    	iter.remove();	//entfernt überflüssige
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
   
   //GET auf einen User durch PathParam
   @GET 
   @Path( "/{userID}" )
   @Produces( MediaType.APPLICATION_XML )
   public Response getOneUser(@PathParam("userID") int i) throws Exception
   {
	   
	    DataHandlerUser handle = new DataHandlerUser();	    
      return Response.status(200).entity(handle.getUserbyID(i)).build() ; //Gibt Meldung 200->"ok" zurück
   }
   
   //POST auf einen neuen User
   @POST 
   @Consumes( MediaType.APPLICATION_XML )
   public Response postNewUser( User user ) throws Exception
   {
	    
	    DataHandlerUser handle = new DataHandlerUser();	    
	    Userlist users = (Userlist) handle.getUsers();
	    List<User> userliste = users.getUser();
	    
	    //ID bestimmen
	    BigInteger id = BigInteger.ZERO ;
		for(User us : userliste ){
			
	    	if(us.getUserID().compareTo(id)==1){
	    		id = us.getUserID();
	    	}
	    }
		user.setUserID(id.add(BigInteger.ONE));
		
	    return Response.created(handle.writeNewUser(user) ).build(); 
   }
   
   //PUT auf einen User um ihn zu ändern
   @PUT
   @Path( "/{userID}" )
   @Consumes( MediaType.APPLICATION_XML )
   public Response changeUser( @PathParam("userID") int id, User user  ) throws Exception
   {
	   DataHandlerUser handle = new DataHandlerUser();	    
      
	   return Response.created(handle.writeUser(user,id) ).build();  
   }
   
   //DELETE auf einen User durch PathParam
   @DELETE
   @Path( "/{userID}" )
   public Response deleteUser( @PathParam("userID") int id  ) throws Exception
   {
	   
	   DataHandlerUser handle = new DataHandlerUser();	      
	   handle.delete(id);
	    
	   return Response.noContent().build() ; //Gibt Meldung 204->"ok" zurück
   }
   
}