package nodepackage;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class XMPPConnect {
	
	public Connection conn = null;
	private static XMPPConnect instance;
	

	//dient zur persistenten Speicherung der Verbindung
	public static XMPPConnect getInstance(){
		if(instance==null){
			instance=new XMPPConnect();
		}
		return instance;
	}
	
	private XMPPConnect(){
		// Create a connection to the jabber.org server on a specific port.
		ConnectionConfiguration config = new ConnectionConfiguration("localhost", 5222);
		conn = new XMPPConnection(config);
		
		try {
			conn.connect();
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void register(String username, String password){
		AccountManager register = new AccountManager(conn);
		try {
			register.createAccount(username, password);
			System.out.println(username+" wurde erstellt!");
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			System.out.println(username+" wurde nicht erstellt!");
			e.printStackTrace();
		}
		
	}
	
	public boolean login(String username, String password){
		System.out.println("einloggen");
		SASLAuthentication.supportSASLMechanism( "PLAIN", 0 );
		try {
			conn.login(username, password);
			System.out.println("Erfolgreich eingeloggt!");
			return true;
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
