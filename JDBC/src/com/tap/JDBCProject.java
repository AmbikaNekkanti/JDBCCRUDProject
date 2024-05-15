package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCProject {
private static Connection connection;
private static PreparedStatement statement;
private static ResultSet res1;

private static Scanner scan = new Scanner(System.in);


public static void main(String[] args) {

	String url = "jdbc:mysql://localhost:3306/jdbcclasses";
	String username = "root";
	String password = "root";
	String SQL1 = "SELECT * from `employee` WHERE `department` = ?";
	String SQL2 = "UPDATE `employee` SET `name` = ? WHERE `id` = ?";
	String SQL3 = "UPDATE `employee` SET `email` = ? WHERE `id` = ?";
	String SQL4 = "UPDATE `employee` SET `salary` = `salary` + ? WHERE `department`= ?";
	String SQL5 = "INSERT into `employee`(`id`,`name`,`email`,`department`,`salary`)values" + "(?,?,?,?,?)";
	String SQL6 = "DELETE from `employee` WHERE `department` = ?";

try {
	connection = DriverManager.getConnection(url, username, password);

	do {
		System.out.println("Enter the Choices");
		System.out.println("1. SELECT");
		System.out.println("2. UPDATE");
		System.out.println("3. INSERT");
		System.out.println("4. DELETE");
		System.out.println("5. Exit");

	// Read user choice
	int choice = scan.nextInt();

	if (choice == 1) {
	// Implement SELECT operation
	System.out.println("Performing SELECT operation...");
	System.out.println();
	Program2.display(connection, statement, null);
	System.out.println();
	// Your SELECT logic here
try {
	connection = DriverManager.getConnection(url, username, password);

	statement = connection.prepareStatement(SQL1);
	System.out.println("Enter Department name :");
	String dept2 = scan.next();
	statement.setString(1, dept2);
	res1 = statement.executeQuery();

	// display
	System.out.println("-------------------------------------------------------------");
	while (res1.next()) {
	int id = res1.getInt("id");
	String name = res1.getString("name");
	String email = res1.getString("email");
	String dept1 = res1.getString("department");
	int salary = res1.getInt("salary");
	// System.out.println(id + " ," + name + ", " + email + " ," + dept + " ," +
	// salary);
	System.out.printf("|%-3d | %-9s | %-20s | %-7s | %d | \n", id, name, 
			email, dept1, salary);
	}
	System.out.println("-------------------------------------------------------------");

} catch (SQLException e) {
	e.printStackTrace();
}

} else if (choice == 2) {
// Implement UPDATE operation
	System.out.println("Performing UPDATE operation...");
	System.out.println();
// Your UPDATE logic here

try {
    connection = DriverManager.getConnection(url, username, password);
    Program2.display(connection, statement, null);
    System.out.println();

    do {
        System.out.println("DO you want to update which column?");
        System.out.println();
        System.out.println("1. name");
        System.out.println("2. email");
        System.out.println("3. salary");
        System.out.println("4. Exit");

        // Read user choice
        int option = scan.nextInt();

        if (option == 1) {
        	statement = connection.prepareStatement(SQL2);
            System.out.println("Enter Id:");
            int id = scan.nextInt();
            System.out.println("Enter New Name:");
            String newName = scan.next();

            System.out.println(statement);
            
            statement.setString(1, newName);
            statement.setInt(2, id);
            System.out.println(statement);
            int i = statement.executeUpdate();
            Program2.display(connection, statement, null);
            System.out.println("Do you want to update more employees? (yes/no)");
        	

        } else if (option == 2) {
        	statement = connection.prepareStatement(SQL3);
            System.out.println("Enter Id:");
            int id = scan.nextInt();
            System.out.println("Enter New Email:");
            String newEmail = scan.next();
            
            System.out.println(statement);
            
            statement.setString(1, newEmail);
            statement.setInt(2, id);
            int i = statement.executeUpdate();
            Program2.display(connection, statement, null);
            System.out.println("Do you want to update more employees? (yes/no)");
        } else if (option == 3) {
        	statement = connection.prepareStatement(SQL4);
            System.out.println("Enter Increment values:");
            int inc = scan.nextInt();
            System.out.println("Enter Department:");
            String dept4 = scan.next();
            
            System.out.println(statement);
            statement.setInt(1, inc);
            statement.setString(2, dept4);
            int i = statement.executeUpdate();
            Program2.display(connection, statement, null);
            System.out.println();
            System.out.println("Do you want to update more employees? (yes/no)");
        }
        else if(option ==4){
     
        System.out.println("Exiting...");
       
        System.exit(0);
        
        }
        else {
        	// If the user enters an invalid choice, assume it's an INSERT operation

        	// Your INSERT logic here
        		System.out.println("Wrong input try again!!!");
        }
    } while (scan.next().equalsIgnoreCase("yes"));
    
  
} catch (SQLException e) {
    e.printStackTrace();
}
}
else if (choice == 3) {
// Implement INSERT operation
System.out.println("Performing INSERT operation...");
System.out.println();
// Your INSERT logic here
try {
	connection = DriverManager.getConnection(url, username, password);
	Program2.display(connection, statement, null);

	statement = connection.prepareStatement(SQL5);
	System.out.println();
	do {

		System.out.println("Enter Id:");
		int id = scan.nextInt();
		System.out.println("Enter Name:");
		String name = scan.next();
		System.out.println("Enter Email:");
		String email = scan.next();
		System.out.println("Enter Department:");
		String dept = scan.next();
		System.out.println("Enter Salary:");
		int salary = scan.nextInt();

		statement.setInt(1, id);
		statement.setString(2, name);
		statement.setString(3, email);
		statement.setString(4, dept);
		statement.setInt(5, salary);

		int i = statement.executeUpdate();
		System.out.println("Do you want to enter more employess? (yes/no)");

} while (scan.next().equalsIgnoreCase("yes"));
Program2.display(connection, statement, null);

System.out.println();

} catch (SQLException e) {
	e.printStackTrace();
}
} else if (choice == 4) {
// Implement DELETE operation
System.out.println("Performing DELETE operation...");
System.out.println();
// Your DELETE logic here
try {
	connection = DriverManager.getConnection(url, username, password);

	Program2.display(connection, statement, null);
	System.out.println();

	statement = connection.prepareStatement(SQL6);

	System.out.println("Enter Department:");
	String dept1 = scan.next();

	statement.setString(1, dept1);
	int i = statement.executeUpdate();
	System.out.println(i + "\n");

	Program2.display(connection, statement, null);

} catch (SQLException e) {
	e.printStackTrace();
}

} else if (choice == 5) {
// Exit the program
System.out.println("Exiting...");
System.exit(0);
} else {
// If the user enters an invalid choice, assume it's an INSERT operation

// Your INSERT logic here
	System.out.println("Wrong input try again!!!");

}

	System.out.println("Do you want to continue (yes/no)?");

} while (scan.next().equalsIgnoreCase("yes"));
	System.out.println("Exit from sql operation.... bye");
}

catch (SQLException e) {
	e.printStackTrace();
}
}
}


























//try {
//	connection = DriverManager.getConnection(url, username, password);
//	Program2.display(connection, statement, null);
//
//	statement = connection.prepareStatement(SQL2);
//	do {
//		System.out.println("DO you want to update which column?");
//		System.out.println("1. name");
//		System.out.println("2. email");
//		System.out.println("3. salary");
//		System.out.println("4. Exit");
//		
//
//		// Read user choice
//	int option = scan.nextInt();
//
//	if (option == 1) {
//		System.out.println("Enter Name:");
//		String name = scan.next();
//		System.out.println("Enter Id:");
//		int id = scan.nextInt();
//
//		statement.setString(1, name);
//		statement.setInt(2, id);
//		int i = statement.executeUpdate();
//		System.out.println("Do you want to update more employees?(yes/no)");
//	}
//	}
//
//	while (scan.next().equalsIgnoreCase("yes"));
//
//	
//} catch (SQLException e) {
//	e.printStackTrace();
//}
//
//try {
//	connection = DriverManager.getConnection(url, username, password);
//	
//
//	statement = connection.prepareStatement(SQL3);
//	do {
//		System.out.println("DO you want to update which column?");
//		System.out.println("1. name");
//		System.out.println("2. email");
//		System.out.println("3. salary");
//		System.out.println("4. Exit");
//		
//		Program2.display(connection, statement, null);
//		// Read user choice
//	int option = scan.nextInt();
//
//	if (option == 2) {
//		System.out.println("Enter Email:");
//		String email = scan.next();
//		System.out.println("Enter Id:");
//		int id = scan.nextInt();
//
//		statement.setString(1, email);
//		statement.setInt(2, id);
//		int i = statement.executeUpdate();
//		System.out.println("Do you want to update more employees?(yes/no)");
//	}
//	}
//	
//	while (scan.next().equalsIgnoreCase("yes"));
//
//	
//} catch (SQLException e) {
//	e.printStackTrace();
//}






