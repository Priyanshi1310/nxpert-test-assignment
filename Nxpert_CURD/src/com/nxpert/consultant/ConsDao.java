package com.nxpert.consultant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsDao {
	private static String jdbcURL = "jdbc:mysql://localhost:3306/nxpert?useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "root";
	
	private static final String SELECT_ALL_CONSULTANT = "select * from consultant";
	private static final String DELETE_CONSULTANT_SQL = "delete from consultant where id = ?;";
	private static final String UPDATE_CONSULTANT_SQL = "update consultant set name = ? where id = ?;";
	
	public ConsDao() {
	}
	
	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static List<Consultant> selectAllConsultant() {

		List<Consultant> consultant = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CONSULTANT);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String customer_name = rs.getString("customer_name");
				String consultant_name = rs.getString("consultant_name");
				consultant.add(new Consultant(id, customer_name,consultant_name));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return consultant;
	}
	
     public boolean updateConsultant(Consultant consultant) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_CONSULTANT_SQL);) {
			statement.setInt(1, consultant.getId());
			statement.setString(2, consultant.getCustomer_name());
			statement.setString(3, consultant.getConsultant_name());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
     

	public boolean deleteConsultant(int id) throws SQLException {
 		boolean rowDeleted;
 		try (Connection connection = getConnection();
 			PreparedStatement statement = connection.prepareStatement(DELETE_CONSULTANT_SQL);) {
 			statement.setInt(1, id);
 			rowDeleted = statement.executeUpdate() > 0;
 		}
 		return rowDeleted;
 	}
	
	private static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	

}
