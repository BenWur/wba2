package nodepackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

/**
 * PubSubController verwaltet die Asynchrone Datenübertragung
 * @author Dario & Ben
 */
public class PubSubController {
	
	private XMPPConnect verbindung;
	// Create a pubsub manager using an existing Connection
    private PubSubManager mgr;
    
    /**
     * Konstruktor
     */
    public PubSubController(){
    	// Create a Connection
    	verbindung= XMPPConnect.getInstance();
    	// Create a pubsub manager using an existing Connection
        mgr = new PubSubManager(verbindung.conn);
    	
    }

    /**
     * Erstellt neue Node
     * @param nodeName
     */
	public void nodeErstellen( String nodeName ){
		
		ConfigureForm form = new ConfigureForm(FormType.submit);
		//Einstellungen
	    form.setAccessModel(AccessModel.open);
	    form.setDeliverPayloads(true);
	    form.setNotifyRetract(true);
	    form.setPersistentItems(true);
	    form.setPublishModel(PublishModel.open);
	    try {
			mgr.getNode(nodeName);	//Überprüft ob Node existiert
			System.out.println("Node existiert");
		} catch (XMPPException e1) {
			System.out.println("Node existiert nicht");
			try {
				LeafNode leaf = (LeafNode) mgr.createNode(nodeName, form);	//legt Node an
				System.out.println("Node angelegt: "+nodeName);
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
     * Veröffentlicht in Node
     * @param nodeName, xml
     */
	public void nodeVeroeffentlichen(String nodeName, String xml){
		LeafNode node = null;
	    // Get the node
	    try {
			node = mgr.getNode(nodeName);
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	    node.publish( new PayloadItem<SimplePayload>(null,new SimplePayload("", "" ,xml)));//eigentliche Veröffentlichung
	}
	
	/**
     * Abonniert eine Node
     * @param nodeName, listener
     */
	public void nodeAbonnieren(String nodeName, ItemEventListener<Item> listener){
		// Get the node
		LeafNode node = null;
	    System.out.println("Node abonniert: "+nodeName);
		try {
			node = mgr.getNode(nodeName);
			node.addItemEventListener(listener);	//fügt Listener hinzu, damit neue Beiträge empfangen werden
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	    try {
			node.subscribe(verbindung.conn.getUser());	//greift direkt auf persistenten User zu und subscribet
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Kündigt einer Node
     * @param nodeName
     */
	public void nodeKuendigen(String nodeName, ItemEventListener<Item> listener){
		// Get the node
		LeafNode node = null;
	    System.out.println("Node gekündigt: "+nodeName);
	    try {
			node = mgr.getNode(nodeName);
			node.removeItemEventListener(listener);		//entfernt Listener
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	    try {
			node.unsubscribe(verbindung.conn.getUser());	//greift direkt auf persistenten User zu
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Liest alle Nodes aus
     */
	public List<String> nodesAuslesen(){
		List<String> nodes=new ArrayList<String>();
		try {
			DiscoverItems item=this.mgr.discoverNodes(null);
			Iterator<DiscoverItems.Item> items = item.getItems();
			while( items.hasNext()){
				nodes.add(items.next().getNode());
			}
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		return nodes;
	}
	
	/**
     * Entfernt alle Nodes. Test-Methode
     */
	public void ereaseAllNodes(){
		List<String> nodes=new ArrayList<String>();
		
		try {
			DiscoverItems item=this.mgr.discoverNodes(null);
			Iterator<DiscoverItems.Item> items = item.getItems();
			while( items.hasNext()){
				nodes.remove(items.next().getNode());
				System.out.println("geloescht!");
			}	
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		
	}
	
}
