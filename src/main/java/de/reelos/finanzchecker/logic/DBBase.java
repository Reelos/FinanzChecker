package de.reelos.finanzchecker.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DBBase<T> {
	private static Connection DBC;
	protected List<T> objectList = new ArrayList<>();

	public DBBase() {
		try {
			Class.forName("org.sqlite.JDBC");
			open();
			close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	protected Connection getConnection() {
		return DBC;
	}

	protected void open() {
		try {
			if (DBC != null && DBC.isClosed()) {
				DBC = DriverManager.getConnection("jdbc:sqlite:" + getClass().getResource("Finanzen.sqlite"));
			} else {
				DBC.close();
				DBC = DriverManager.getConnection("jdbc:sqlite:" + getClass().getResource("Finanzen.sqlite"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract int create(String... args);
	public abstract void update(T t);
	public abstract void delete(T t);
	public abstract T read(String id);
	public abstract List<T> readAll();

	protected void close() {
		try {
			if (DBC != null && !DBC.isClosed()) {
				DBC.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
