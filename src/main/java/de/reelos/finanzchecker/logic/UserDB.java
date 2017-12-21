package de.reelos.finanzchecker.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import de.reelos.finanzchecker.logic.dbobj.User;

public class UserDB extends DBBase<User> {

	private static final UserDB INSTANCE = new UserDB();

	private UserDB() {
		super();
		open();
		try {
			Connection con = getConnection();

			StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS Users\r\n");
			query.append("(\r\n");
			query.append("uid SERIAL PRIMARY KEY,\r\n");
			query.append("name VARCHAR(32),\r\n");
			query.append("fname VARCHAR(32),\r\n");
			query.append("deleted BOOLEAN DEFAULT FALSE\r\n");
			query.append(");\r\n");

			con.prepareStatement(query.toString()).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	protected int create(String... args) {
		int result = 0;
		String name = args[0], fname = args[1];
		open();
		try {
			Connection con = getConnection();

			StringBuilder query = new StringBuilder("INSERT INTO Users\r\n");
			query.append("(name, fname)\r\n");
			query.append("VALUES\r\n");
			query.append("(?,?)\r\n");

			PreparedStatement ps = con.prepareStatement(query.toString());
			ps.setString(1, name);
			ps.setString(2, fname);
			ps.executeUpdate();

			query = new StringBuilder("SELECT\n");
			query.append("uid\n");
			query.append("FROM\n");
			query.append("Users\n");
			query.append("ORDER BY\n");
			query.append("uid DESC\n");
			query.append("LIMIT 1\n");

			ResultSet set = con.createStatement().executeQuery(query.toString());
			if (set.next()) {
				result = set.getInt("uid");
			}
			objectList.add(new User(result, name, fname));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return result;
	}

	public int create(String name, String fname) {
		return create(name, fname);
	}

	@Override
	public void update(User t) {
		open();
		try {
			Connection con = getConnection();

			StringBuilder query = new StringBuilder("UPDATE Users\r\n");
			query.append("SET name = ?, fname = ?)\r\n");
			query.append("WHERE\r\n");
			query.append("uid = ?\r\n");

			PreparedStatement ps = con.prepareStatement(query.toString());
			ps.setString(1, t.getName());
			ps.setString(2, t.getFamilyName());
			ps.setInt(3, t.getUserID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public void delete(User t) {
		open();
		try {
			Connection con = getConnection();

			StringBuilder query = new StringBuilder("UPDATE Users\r\n");
			query.append("SET deleted = true)\r\n");
			query.append("WHERE\r\n");
			query.append("uid = ?\r\n");

			PreparedStatement ps = con.prepareStatement(query.toString());
			ps.setInt(1, t.getUserID());
			ps.executeUpdate();

			objectList.remove(t);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public User read(String id) {
		return objectList.stream()
				.filter(f -> (f.getName() == id || f.getFamilyName() == id || String.valueOf(f.getUserID()) == id))
				.findFirst().orElse(null);
	}

	@Override
	public List<User> readAll() {
		open();
		try {
			objectList.clear();

			Connection con = getConnection();

			StringBuilder query = new StringBuilder("SELECT\r\n");
			query.append("uid, name, fname\r\n");
			query.append("FROM\r\n");
			query.append("Users\r\n");
			query.append("WHERE\r\n");
			query.append("deleted = false\r\n");

			ResultSet rs = con.prepareStatement(query.toString()).executeQuery();
			while (rs.next()) {
				objectList.add(new User(rs.getInt("uid"), rs.getString("name"), rs.getString("fname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return objectList;
	}

	public static UserDB getInstance() {
		return INSTANCE;
	}
}
