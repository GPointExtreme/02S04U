package org.campus02.addressbuch;

import java.io.IOException;

public class AddressDemo {

	public static void main(String[] args) throws AddressExportException, IOException, AddressLoadException, AddressLoadWrongException, AddressExportFileAlreadyExistsException {
		
		AddressManager manager = new AddressManager();
		manager.add(new Address("Dominik", "Geiger", "0664123456789", "dominik@mail.com"));
		manager.add(new Address("David", "Erlacher", "0664987654321", "david@mail.com"));
		
		manager.exportToCsv("/temp/address.txt", ";");
		
		manager.loadFromCsv("/temp/address.txt", ";");
		
	}

}
