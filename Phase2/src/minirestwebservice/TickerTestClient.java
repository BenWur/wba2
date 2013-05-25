package minirestwebservice;

import com.sun.jersey.api.client.*;

public class TickerTestClient
{
   public static void main( String[] args )
   {
      String url = ( args.length > 0 ) ? args[0] : "http://localhost:4434";
      String nam = ( args.length > 1 ) ? args[1] : "ich";
      url = url + "/users?name=" + nam;
      System.out.println( "URL: " + url );

      WebResource wrs = Client.create().resource( url );

      
      System.out.println( "\nHTML-Ausgabe:" );
      System.out.println( wrs.accept( "text/html"  ).get( String.class ) );
   }
}