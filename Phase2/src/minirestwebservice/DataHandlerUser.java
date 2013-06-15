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

public class DataHandlerUser {
	private Userlist users = null;
	private Marshaller marshaller = null;

	public DataHandlerUser() {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Userlist.class);
			Unmarshaller um = jc.createUnmarshaller();
			users = (Userlist) um.unmarshal(new File("XML/Userlist.xml"));
			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public Userlist getUsers() {
		return this.users;
	}

	public User getUserbyID(int id) {
		return this.users.getUser().get(id - 1);
	}

	public URI writeNewUser(User user) {

		List<User> userliste = users.getUser();
		userliste.add(user);
		
		this.savePersistent();
		return URI.create("http://localhost:4434/users/" + user.getUserID().toString());

	}

	public URI writeUser(User user, int id) {

		List<User> userliste = users.getUser();

		int i = 0;
		
		for (User us : userliste) {
			if (us.getUserID().equals(id)) {
				userliste.set(i, user);
			}
			i++;
		}

		this.savePersistent();

		return URI.create("http://localhost:4434/users/" + id);

	}

	public void delete(int id) {

		List<User> userliste = users.getUser();

		int i = 0;
		for (User us : userliste) {
			if (us.getUserID().equals(id)) {
				userliste.remove(i);
			}
			i++;
		}

		this.savePersistent();

	}

	private void savePersistent() {
		try {
			marshaller.marshal(users, new File("XML/Userlist.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
