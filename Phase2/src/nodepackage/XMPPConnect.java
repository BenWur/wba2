package nodepackage;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * Dient zur Verwaltung der Verbindung zum Server
 *
 * @author Dario & Ben
 */
public class XMPPConnect {

    public Connection conn = null;
    private static XMPPConnect instance;

    /**
     * Dient zur persistenten Speicherung der Verbindung
     */
    public static XMPPConnect getInstance() {
        if (instance == null) {
            instance = new XMPPConnect();
        }
        return instance;
    }

    /**
     * Konstruktor
     */
    private XMPPConnect() {
        // Create a connection to the jabber.org server on a specific port.
        ConnectionConfiguration config = new ConnectionConfiguration("localhost", 5222);
        conn = new XMPPConnection(config);
        try {
            conn.connect();
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    /**
     * Erstellt neuen Nutzer auf dem Server
     *
     * @param username, password
     */
    public void register(String username, String password) {
        AccountManager register = new AccountManager(conn);
        try {
            register.createAccount(username, password);
            System.out.println(username + " wurde erstellt!");
        } catch (XMPPException e) {
            System.out.println(username + " wurde nicht erstellt!");
            e.printStackTrace();
        }
    }

    /**
     * Logt einen Nutzer ein
     *
     * @param username, password
     */
    public boolean login(String username, String password) {
        System.out.println("einloggen");
        SASLAuthentication.supportSASLMechanism("PLAIN", 0);
        try {
            conn.login(username, password);
            System.out.println("Erfolgreich eingeloggt!");
            return true;
        } catch (XMPPException e) {
            e.printStackTrace();
            return false;
        }
    }
}
