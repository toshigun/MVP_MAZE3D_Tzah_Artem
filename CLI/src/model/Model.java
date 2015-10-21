package model;


import view.Data.Data;

/**
 * @author tzah and Artem
 *	Interface for Model Classes
 */
public interface Model {

	void makeChanges(Data d);

	void fetchData(String Command, String name, String parameters);
	
	
}
