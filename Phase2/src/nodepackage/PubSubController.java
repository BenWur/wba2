package nodepackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;

public class PubSubController {
	
	private XMPPConnect verbindung;
	// Create a pubsub manager using an existing Connection
    private PubSubManager mgr;
    private ItemEventCoordinator listener = new ItemEventCoordinator();
    
    public PubSubController(){
    	// Create a Connection
    	verbindung= XMPPConnect.getInstance();
    	// Create a pubsub manager using an existing Connection
        mgr = new PubSubManager(verbindung.conn);
    	
    }

    // Create the node
	public void nodeErstellen( String nodeName ){
		
		ConfigureForm form = new ConfigureForm(FormType.submit);

	    form.setAccessModel(AccessModel.open);
	    form.setDeliverPayloads(true);
	    form.setNotifyRetract(true);
	    form.setPersistentItems(true);
	    form.setPublishModel(PublishModel.open);
	    try {
			mgr.getNode(nodeName);
			System.out.println("Node existiert");
		} catch (XMPPException e1) {
			System.out.println("Node existiert nicht");

		    try {
				LeafNode leaf = (LeafNode) mgr.createNode(nodeName, form);
				
				System.out.println("Node angelegt: "+nodeName);

			} catch (XMPPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void nodeVeroeffentlichen(String nodeName, String xml){
		LeafNode node = null;
	    // Get the node
	    System.out.println("Node veröffentlichen: "+nodeName);
	    try {
			node = mgr.getNode(nodeName);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    node.publish( new PayloadItem<SimplePayload>(null,new SimplePayload("", "" ,xml)));
	    
	    

	}
	
	public void nodeAbonnieren(String nodeName){
		// Get the node
		LeafNode node = null;
	      System.out.println("Node gejoint: "+nodeName);
	      
		try {
			node = mgr.getNode(nodeName);
			node.addItemEventListener(listener);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	    
		try {
			node.subscribe(verbindung.conn.getUser());	//greift direkt auf persistenten User zu
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nodeKuendigen(String nodeName){
		// Get the node
		LeafNode node = null;
	    System.out.println("Node gekündigt: "+nodeName);
	    
		try {
			node = mgr.getNode(nodeName);
			node.removeItemEventListener(listener);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		try {
			node.unsubscribe(verbindung.conn.getUser());	//greift direkt auf persistenten User zu
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> nodesAuslesen(){
		List<String> nodes=new ArrayList<String>();
		
		try {
			DiscoverItems item=this.mgr.discoverNodes(null);
			
			Iterator<DiscoverItems.Item> items = item.getItems();
			
			while( items.hasNext()){
				nodes.add(items.next().getNode());
			}
			
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodes;
	}
	
}
