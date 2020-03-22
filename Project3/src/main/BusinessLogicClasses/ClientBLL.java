package BusinessLogicClasses;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import DAOClasses.ClientDAO;
import Model.Clientul;

public class ClientBLL {
	private ClientDAO client;

	public ClientBLL() {
		super();
		client = new ClientDAO();
	}

	public ClientDAO getClient() {
		return client;
	}

	public void setClient(ClientDAO client) {
		this.client = client;
	}

	public boolean validareDate(Clientul c) {
		if (c.getNume().length() > 30) {
			System.out.println("Numar caractere prea mare pentru nume !\n");
			return false;
		}
		if (c.getAdresa().length() > 30) {
			System.out.println("Numar caractere prea mare pentru adreasa !\n");
			return false;

		}
		if (c.getEmail().length() > 30 || c.getEmail().contains("@yahoo.com") == false) {
			System.out.println("Numar careactere prea mare pentru adreasa email sau adresa invalida !\n");
			return false;
		}
		return true;
	}

	public void insertNewClient(Clientul c) {
		if (validareDate(c)) {
			if (this.getClient().findById(c.getIdClient()) != null) {
				System.out.println("Client existent !");
			} else {
				client.insertItem(c);
			}
		}
	}

	public void deleteClient(int id) {
		if (client.findById(id) == null) {
			System.out.println("Client inexistent !");
		} else {
			client.deleteItem(id);
		}
	}

	public void updateClient(Clientul cNou) {
		if (this.getClient().findById(cNou.getIdClient()) == null) {
			System.out.println("Client inexistent !");
		} else {
			String field1 = "", field2 = "", val1 = "", val2 = "";
			Clientul cVechi = client.findById(cNou.getIdClient());
			if (validareDate(cNou) == true) {
				if (cVechi.getNume().equals(cNou.getNume()) == false) {
					field1 = "nume";
					val1 = cNou.getNume();
				} else if (cVechi.getAdresa().equals(cNou.getAdresa()) == false) {
					field1 = "adresa";
					val1 = cNou.getAdresa();
				} else if (cVechi.getEmail().equals(cNou.getEmail()) == false) {
					field1 = "email";
					val1 = cNou.getEmail();
				}
				field2 = "idClient";
				val2 = String.valueOf(cVechi.getIdClient());
				client.updateItems(field1, field2, val1, val2);
			}
		}
	}

	public Object[][] viewTabel() {
		List<Clientul> clienti = new ArrayList<Clientul>();
		Object[][] tabel = new Object[1000][1000];
		clienti = client.listTable();
		int linie = 0;
		for (Clientul c : clienti) {
			int coloana = 0;
			for (Field field : Clientul.class.getDeclaredFields()) {
				field.setAccessible(true);
				try {
					tabel[linie][coloana] = field.get(c);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				coloana++;
			}
			linie++;
		}
		return tabel;
	}
}
