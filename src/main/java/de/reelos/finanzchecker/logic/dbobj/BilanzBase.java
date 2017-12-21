package de.reelos.finanzchecker.logic.dbobj;

import java.util.Date;

import de.reelos.finanzchecker.logic.UserDB;
import javafx.beans.property.*;

public abstract class BilanzBase {
	public enum Type {
		EINMALIG, MONATLICH, JAEHRLICH
	}
	private final ReadOnlyIntegerProperty auftragIDProperty;
	private final ObjectProperty<Date> createDateProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Date> executeDateProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Date> endDateProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Type> typeProperty = new SimpleObjectProperty<>();
	private final DoubleProperty valueProperty = new SimpleDoubleProperty();
	private final ObjectProperty<User> userProperty = new SimpleObjectProperty<>();
	
	public BilanzBase(int id, Date createDate, Date executeDate, Date endDate, Type type, double value, int uid) {
		auftragIDProperty = new SimpleIntegerProperty(id);
		createDateProperty.set(createDate);
		executeDateProperty.set(executeDate);
		endDateProperty.set(endDate);
		valueProperty.set(value);
		userProperty.set(UserDB.getInstance().read(String.valueOf(uid)));
	}
	
	public ReadOnlyIntegerProperty auftragIDProperty() {
		return auftragIDProperty;
	}
	
	public int getAuftragID() {
		return auftragIDProperty.get();
	}
	
	public ObjectProperty<Date> createDateProperty() {
		return createDateProperty;
	}
	
	public Date getCreateDate() {
		return createDateProperty.get();
	}
	
	public void setCreateDate(Date date) {
		createDateProperty.set(date);
	}
	
	public ObjectProperty<Date> executeDateProperty() {
		return executeDateProperty;
	}
	
	public Date getExecuteDate() {
		return executeDateProperty.get();
	}
	
	public void setExecuteDate(Date date) {
		executeDateProperty.set(date);
	}
	
	public ObjectProperty<Date> endDateProperty() {
		return endDateProperty;
	}
	
	public Date getEndDate() {
		return endDateProperty.get();
	}
	
	public void setEndDate(Date date) {
		endDateProperty.set(date);
	}
	
	public ObjectProperty<Type> typeProperty() {
		return typeProperty;
	}
	
	public Type getType() {
		return typeProperty.get();
	}
	
	public void setType(Type date) {
		typeProperty.set(date);
	}
	
	public DoubleProperty valueProperty() {
		return valueProperty;
	}
	
	public Double getValue() {
		return valueProperty.get();
	}
	
	public void setValue(double value) {
		valueProperty.set(value);
	}
	
	public ObjectProperty<User> userProperty() {
		return userProperty;
	}
	
	public User getUser() {
		return userProperty.get();
	}
	
	public void setUser(User date) {
		userProperty.set(date);
	}
}
