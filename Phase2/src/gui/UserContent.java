/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import userlist.User;
import userlist.Userlist;

/**
 *
 * @author Dario
 */
public class UserContent {
    
    public User userInfo(String username)
	   {   
	       String url = "http://localhost:4434/users?name="+username;
	       WebResource wrs = Client.create().resource(url);
	       
	       Userlist ev = wrs.accept("application/xml").get(Userlist.class);
	        
	     return ev.getUser().get(0);
	    }
}