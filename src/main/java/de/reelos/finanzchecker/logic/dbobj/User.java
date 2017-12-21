package de.reelos.finanzchecker.logic.dbobj;

import de.reelos.finanzchecker.logic.UserDB;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private final ReadOnlyIntegerProperty userIDProperty;
	private final StringProperty nameProperty = new SimpleStringProperty();
	private final StringProperty familyNameProperty = new SimpleStringProperty();
	private final UserDB db = UserDB.getInstance();

	public User(int id, String name, String fname) {
		int uid = id;
		if(id <= 0)
		{
			uid = db.create(name, fname);
		}
		userIDProperty = new SimpleIntegerProperty(uid);
		nameProperty.set(name);
		familyNameProperty.set(fname);
	}

	public int getUserID() {
		return userIDProperty.get();
	}

	public ReadOnlyIntegerProperty userIDProperty() {
		return userIDProperty;
	}

	public String getName() {
		return nameProperty.get();
	}

	public StringProperty nameProperty() {
		return nameProperty;
	}

	public void setName(String name) {
		nameProperty.set(name);
		db.update(this);
	}

	public String getFamilyName() {
		return familyNameProperty.get();
	}

	public StringProperty familyNameProperty() {
		return familyNameProperty;
	}

	public void setFamilyName(String name) {
		familyNameProperty.set(name);
		db.update(this);
	}
}
