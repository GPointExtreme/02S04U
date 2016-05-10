package org.campus02.addressbuch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AddressManager {
	
	private ArrayList<Address> list = new ArrayList<>();

	public ArrayList<Address> getList() {
		return list;
	}
	
	public void add(Address a) {
		list.add(a);
	}
	
	public void loadFromCsv(String path, String separator) throws AddressLoadException, AddressLoadWrongException {
		File file = new File(path);
		try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while((line = br.readLine()) != null) {
				String[] columns = line.split(separator);
				
				if(columns.length != 4) {
					throw new AddressLoadWrongException();
				}
				
				Address a = new Address(columns[0], columns[1], columns[2], columns[3]);
				list.add(a);
			} 
		} 
		catch(FileNotFoundException e) {
			throw new AddressLoadException(e);
		}
		catch(IOException e1) {
			throw new AddressLoadException(e1);
		}
	}
	
	public void exportToCsv(String path, String separator) throws AddressExportException, 
			IOException, AddressExportFileAlreadyExistsException {
		File file = new File(path);
		if(file.exists()) {
			throw new AddressExportFileAlreadyExistsException();
		}
		try(FileWriter fw = new FileWriter(file); PrintWriter pw = new PrintWriter(fw)) {
			for (Address address : list) {
				String line = address.getFirstname() + separator + 
						address.getLastname() + separator +
						address.getMobileNumber() + separator +
						address.getEmail();
				pw.println(line);
			}
			pw.println();
		} catch (IOException e) {
			throw new AddressExportException(e);
		} 
	}

}
