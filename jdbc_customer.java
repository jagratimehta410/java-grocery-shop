package shoopkeper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class jdbc_customer {
	private static final String USERNAME = "root";
    private static final String PASSWORD = "jag@1234";
    private static final String DataBaseUrl="jdbc:mysql://localhost:3300/shoopkeper?autoReconnect=true&useSSL=false";
    
    public void purchase() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the product ID: ");
        int productId = input.nextInt();
        System.out.print("Enter the quantity: ");
        int quantity = input.nextInt();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection1 = DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection1.prepareStatement("select * from products where id = ?")) {

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Product not found.");
                return;
            }
            String productName = resultSet.getString("name");
            double productPrice = resultSet.getDouble("price");

            try (Connection connection2 = DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);
                 PreparedStatement insertStatement = connection2.prepareStatement("insert into purchase values (?,?,?,?)")) {

                insertStatement.setInt(1, productId);
                insertStatement.setString(2, productName);
                insertStatement.setDouble(3, productPrice * quantity); 
                insertStatement.setInt(4, quantity);

                insertStatement.executeUpdate();

                System.out.println("Product purchased successfully!");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void order() {
    	try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    try (Connection connection1 = DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);) {
        String sql = "select * from purchase";
        Statement statement = connection1.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
        System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity);
        }
    } catch (SQLException e1) {
        e1.printStackTrace();
    }
  }
public void payment() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	try {
    		Connection connection1 =DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);
    		String sql = "SELECT sum(purchase.price*purchase.quantity) as TotalAmount FROM purchase;";
    		Statement st=connection1.createStatement();
            ResultSet rs1 = st.executeQuery(sql);
            
            rs1.next();
       	    System.out.println("Total Amount : "+ rs1.getString(1));
             
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
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
         PreparedStatement preparedStatement = connection1.prepareStatement("delete from purchase where id = ?")) {

        preparedStatement.setInt(1, productId);

        preparedStatement.executeUpdate();

        System.out.println(" purchase-Product deleted successfully!");
    } catch (SQLException e1) {
        e1.printStackTrace();
    }
}

public  void productlist(){
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
         try (Connection connection1 = DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);) 
         {   
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
	 
}

