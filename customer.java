package shoopkeper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class customer {
	Scanner sc = new Scanner(System.in);
	private static final String DBURL="jdbc:mysql://localhost:3306?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DataBaseUrl="jdbc:mysql://localhost:3306/testwDb?autoReconnect=true&useSSL"
    		+ "=false";
    
	public void purchase() {
		System.out.println("\nEnter the Order Id");
		int order_id = sc.nextInt();
		System.out.println("Enter the Product Id");
		int product_id = sc.nextInt();
		System.out.println("Enter the Quantity");
		int quantity = sc.nextInt();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			Connection connection1 =DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);
			String sql = "insert into purchase(quantity,product_id,order_id) values(?,?,?)";
	           PreparedStatement preparedStatement = connection1.prepareStatement(sql);
	           preparedStatement.setInt(1, quantity);
	           preparedStatement.setInt(2,product_id);
	           preparedStatement.setInt(3, order_id);
	           
	           
	          int a = preparedStatement.executeUpdate();
	           
	           if(a!=0) {
	        	   System.out.println("Add to Cart Succesfully");	           }
	           else {
	        	   System.out.println("Not Add try Again");	   
	           }
	           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void AddtoCart() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			Connection connection1 =DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);
			String sql = "SELECT shop.product_id, shop.product_name, shop.price,purchase.quantity FROM shop INNER JOIN purchase ON shop.product_id=purchase.product_id;";
			Statement st=connection1.createStatement();
	        ResultSet rs = st.executeQuery(sql); 
	   
	         while(rs.next()) {
	        	 System.out.println("Product_id : "+ rs.getString(1));
	        	 System.out.println("Product_name : "+ rs.getString(2));
	        	 System.out.println("Price : "+ rs.getString(3));
	        	 System.out.println("Quantity : "+ rs.getString(4));
	        	 
	         }
	          
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkout() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			Connection connection1 =DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);
			String sql = "SELECT shop.product_id,shop.price,purchase.quantity,(shop.price*purchase.quantity) as Amount FROM shop INNER JOIN purchase ON shop.product_id=purchase.product_id;";
			Statement st=connection1.createStatement();
//			String sql1 = "SELECT sum(shop.price*purchase.quantity) as TotalAmount FROM shop INNER JOIN purchase ON shop.product_id=purchase.product_id;";
//	        ResultSet rs1 = st.executeQuery(sql1);
	        ResultSet rs = st.executeQuery(sql); 
	   
	         while(rs.next()) {
	        	 System.out.println("Product_id : "+ rs.getString(1));
	        	 System.out.println("Price : "+ rs.getString(2));
	        	 System.out.println("Quantity : "+ rs.getString(3));
	        	 System.out.println("Amount : "+ rs.getString(4));    
	         }
	         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


public void payment() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		Connection connection1 =DriverManager.getConnection(DataBaseUrl, USERNAME, PASSWORD);
		String sql1 = "SELECT sum(shop.price*purchase.quantity) as TotalAmount FROM shop INNER JOIN purchase ON shop.product_id=purchase.product_id;";
		Statement st=connection1.createStatement();
        ResultSet rs1 = st.executeQuery(sql1);
        
        rs1.next();
   	    System.out.println("Total Amount : "+ rs1.getString(1));
   
        
         
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
