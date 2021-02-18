package com.nxpert.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustDao {
	
	private static String jdbcURL = "jdbc:mysql://localhost:3306/nxpert?useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "root";
	
	private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customer (id, name) VALUES (?, ?)";
	private static final String SELECT_ALL_CUSTOMER = "select * from customer";
	private static final String DELETE_CUSTOMER_SQL = "delete from customer where id = ?;";
	private static final String UPDATE_CUSTOMER_SQL = "update customer set name = ? where id = ?;";
	
	public CustDao() {
	}
	
	protected static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public boolean insertCustomer(Customer customer) throws SQLException {
        Boolean rowInserted;
        try(Connection connection = getConnection();
         
        PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER_SQL);){
        statement.setInt(1, customer.getId());
        statement.setString(2, customer.getName());
         rowInserted = statement.executeUpdate() > 0;
        }
        return rowInserted;
    }
	
	public static List<Customer> selectAllCustomer() {

		List<Customer> customer = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				customer.add(new Customer(id, name));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return customer;
	}
	
	public List<Customer> viewCustomer(Customer customer) {

		List<Customer> customers = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				customers.add(new Customer(id, name));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return customers;
	}
	
     public boolean updateCustomer(Customer customer) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);) {
			statement.setInt(1, customer.getId());
			statement.setString(4, customer.getName());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
     
     public boolean deleteCustomer(int id) throws SQLException {
 		boolean rowDeleted;
 		try (Connection connection = getConnection();
 				PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_SQL);) {
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
