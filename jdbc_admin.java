package shoopkeper;

import java.sql.*;
import java.util.*;

public class jdbc_admin {
	private static final String USERNAME = "root";
    private static final String PASSWORD = "jag@1234";
    private static final String DataBaseUrl="jdbc:mysql://localhost:3300/shoopkeper?autoReconnect=true&useSSL=false";
    
     public  void productlist(){
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
         try (Connection connection1 = DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);) {   
             String sql = "select * from products";
             Statement statement = connection1.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
             
             while (resultSet.next()) {
                 int id = resultSet.getInt("id");
                 String name = resultSet.getString("name");
                 double price = resultSet.getDouble("price");
                 
                 System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price);
             }
         } catch (SQLException e1) {
             e1.printStackTrace();
         }
      }
     public void insert() {
    	    Scanner input = new Scanner(System.in);
    	    System.out.print("Enter the product ID: ");
    	    int productId = input.nextInt();
    	    System.out.print("Enter the product name: ");
    	    String productName = input.next();
    	    System.out.print("Enter the product price: ");
    	    double productPrice = input.nextDouble();

    	    try {
    	        Class.forName("com.mysql.cj.jdbc.Driver");
    	    } catch (ClassNotFoundException e) {
    	        e.printStackTrace();
    	    }
    	    try (Connection connection1 = DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);) {
    	        String sql = "insert into products values(?,?,?)";

    	        PreparedStatement preparedStatement = connection1.prepareStatement(sql);
    	        preparedStatement.setInt(1, productId);
    	        preparedStatement.setString(2, productName);
    	        preparedStatement.setDouble(3, productPrice);

    	        preparedStatement.executeUpdate();

    	        System.out.println("Product added successfully!");
    	    } catch (SQLException e1) {
    	        e1.printStackTrace();
    	    }
    	}
     public void update() {
    	    Scanner input = new Scanner(System.in);
    	    System.out.print("Enter the product ID: ");
    	    int productId = input.nextInt();
    	    System.out.print("Enter the new product name: ");
    	    String productName = input.next();
    	    System.out.print("Enter the new product price: ");
    	    double productPrice = input.nextDouble();

    	    try {
    	        Class.forName("com.mysql.cj.jdbc.Driver");
    	    } catch (ClassNotFoundException e) {
    	        e.printStackTrace();
    	    }

    	    try (Connection connection1 = DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);
    	         PreparedStatement preparedStatement = connection1.prepareStatement("update products set name = ?, price = ? where id = ?")) {

    	        preparedStatement.setString(1, productName);
    	        preparedStatement.setDouble(2, productPrice);
    	        preparedStatement.setInt(3, productId);

    	        preparedStatement.executeUpdate();

    	        System.out.println("Product updated successfully!");
    	    } catch (SQLException e1) {
    	        e1.printStackTrace();
    	    }
    	}
     public void delete() {
    	    Scanner input = new Scanner(System.in);
    	    System.out.print("Enter the product ID: ");
    	    int productId = input.nextInt();

    	    try {
    	        Class.forName("com.mysql.cj.jdbc.Driver");
    	    } catch (ClassNotFoundException e) {
    	        e.printStackTrace();
    	    }

    	    try (Connection connection1 = DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);
    	         PreparedStatement preparedStatement = connection1.prepareStatement("delete from products where id = ?")) {

    	        preparedStatement.setInt(1, productId);

    	        preparedStatement.executeUpdate();

    	        System.out.println("Product deleted successfully!");
    	    } catch (SQLException e1) {
    	        e1.printStackTrace();
    	    }
    	}
       
}
