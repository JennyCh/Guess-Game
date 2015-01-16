package guessGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnect {

	Connection c = null;
	Statement stmt = null;

	public DatabaseConnect() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:players.db");
		System.out.println("Connected to database successfully");
	}

	public void createTable() throws SQLException {
		stmt = c.createStatement();
		String sql = "CREATE TABLE PLAYER (NAME TEXT NOT NULL,"
				+ " PASSWORD TEXT NOT NULL, POINTS INT NOT NULL)";
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public boolean insertPlayer(String name, String password) throws SQLException {
		if (!hasUserName(name)) {
			stmt = c.createStatement();
			String sql = "INSERT INTO PLAYER (NAME,PASSWORD,POINTS) "
					+ "VALUES ('" + name + "', '" + password + "', 0);";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			return true;
		}
		return false;
	}

	public boolean hasPlayer(String name, String password) throws SQLException {
		stmt = c.createStatement();
		String strQuery = "SELECT NAME FROM PLAYER WHERE NAME = '" + name
				+ "' AND PASSWORD = '" + password + "';";
		System.out.println(strQuery);
		ResultSet rs = stmt.executeQuery(strQuery);
		if (rs.next()) {
			rs.close();
			stmt.close();
			return true;
		}
		rs.close();
		stmt.close();
		return false;
	}

	public boolean hasUserName(String name) throws SQLException {
		stmt = c.createStatement();
		String strQuery = "SELECT NAME FROM PLAYER WHERE NAME = '" + name
				+ "';";
		System.out.println(strQuery);
		ResultSet rs = stmt.executeQuery(strQuery);
		if (rs.next()) {
			rs.close();
			stmt.close();
			return true;
		}
		rs.close();
		stmt.close();
		return false;
	}

	public void addPoints(String name, String password) throws SQLException {
		int points = getPlayerPoints(name, password);
		points += 5;

		stmt = c.createStatement();
		String sql = "UPDATE PLAYER SET POINTS = " + points + " WHERE NAME = '"
				+ name + "' AND PASSWORD = '" + password + "';";
		System.out.println(sql);
		stmt.executeUpdate(sql);
	}

	public int getPlayerPoints(String name, String password)
			throws SQLException {
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT POINTS FROM PLAYER "
				+ "WHERE NAME='" + name + "' AND PASSWORD = '" + password
				+ "';");
		rs.next();
		int points = rs.getInt("POINTS");
		rs.close();
		stmt.close();
		return points;
	}

	public int getTopScore() throws SQLException {
		stmt = c.createStatement();
		String sql = "SELECT MAX(POINTS) AS TOP_SCORE FROM PLAYER";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			return rs.getInt("TOP_SCORE");
		}
		rs.close();
		stmt.close();
		return 0;
	}

	public static void main(String args[]) throws ClassNotFoundException,
			SQLException {
		DatabaseConnect db = new DatabaseConnect();
		// db.createTable();
		System.out.println(db.getPlayerPoints("MMandel", "1234"));
		System.out.println(db.getPlayerPoints("Miriam", "yellow"));
		System.out.println(db.getTopScore());
	}
}
