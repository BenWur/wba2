package minirestwebservice;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyServerFactory;

/**
 * Server unserer Anwendung
 * @author Ben & Dario
 */

public class SocialTickerServer
{
   public static void main( String[] args ) throws Exception
   {
      String url = "http://localhost:4434";	//Url des Servers

      SelectorThread srv = GrizzlyServerFactory.create( url );	//erstellt GrizzlyServer

      System.out.println( "URL: " + url );
      
      System.out.println( "Enter to stop server" );
      System.in.read();
      srv.stopEndpoint();	//damit der Server erst endet, sobald man enter eingibt
      System.out.println( "Server stopped" );

   }
}