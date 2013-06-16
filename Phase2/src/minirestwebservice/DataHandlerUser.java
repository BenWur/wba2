package minirestwebservice;

import java.io.File;
import java.net.URI;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import userlist.User;
import userlist.Userlist;

/**
 * DataHandler zum verwalten der Restabfragen der User
 * @author Ben & Dario
 *
 */

public class DataHandlerUser {
	private Userlist users = null;
	private Marshaller marshaller = null;

	public DataHandlerUser() {
		JAXBContext jc;
		try {
			//benötigte Objekte
			jc = JAXBContext.newInstance(Userlist.class);
			Unmarshaller um = jc.createUnmarshaller();
			users = (Userlist) um.unmarshal(new File("XML/Userlist.xml"));
			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	//gibt alle User zurück
	public Userlist getUsers() {
		return this.users;
	}
	//gibt gestimmten User zurück
	public User getUserbyID(int id) {
		return this.users.getUser().get(id - 1);
	}
	//schreibt neuen User
	public URI writeNewUser(User user) {

		List<User> userliste = users.getUser();
		userliste.add(user);	//fügt User zur Liste hinzu
		
		this.savePersistent();
		return URI.create("http://localhost:4434/users/" + user.getUserID().toString());

	}
	//ändert bestimmten User 
	public URI writeUser(User user, int id) {

		List<User> userliste = users.getUser();

		int i = 0;
		//Abfrage für den bestimmten User
		for (User us : userliste) {
			if (us.getUserID().intValue()==id) {
				userliste.set(i, user);	//ändert User an der Stelle i
			}
			i++;
		}

		this.savePersistent();

		return URI.create("http://localhost:4434/users/" + id);

	}
	//löscht bestimmten User 
	public void delete(int id) {

		List<User> userliste = users.getUser();

		int i = 0;	//Zählvariable
		//Abfrage um User zu bestimmen
		for (User us : userliste) {
			if (us.getUserID().intValue()==id) {
				userliste.remove(i);	//entfernt User an der Stelle i
			}
			i++;
		}

		this.savePersistent();

	}
	//speichert ab
	private void savePersistent() {
		try {
			marshaller.marshal(users, new File("XML/Userlist.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
