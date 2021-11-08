package com.example.Bank;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class BankApplication {

	@SneakyThrows
	public static void main(String[] args) {
		createDB();
		SpringApplication.run(BankApplication.class, args);
	}

	private static void createDB() throws SQLException {

		Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:Bank", "SA", "");
		if (c!= null){
			System.out.println("Connection created successfully");

		}else{
			System.out.println("Problem with creating connection");
		}
		Statement st = c.createStatement();

		int res = st.executeUpdate("CREATE TABLE clients (" +
				"id INT NOT NULL, surname VARCHAR(50) NOT NULL," +
				" name VARCHAR(50) NOT NULL, otchestvo VARCHAR(50) NOT NULL , phoneNumber VARCHAR(50) NOT NULL," +
				"mail VARCHAR(50) NOT NULL, passportNumber VARCHAR(50) NOT NULL,PRIMARY KEY (id));" +
				"");

//		res = st.executeUpdate("INSERT INTO clients VALUES (0,'Fam', 'Nik', 'Alex', 123, 'asd@mai.ru', 1234);");

		res = st.executeUpdate("CREATE TABLE credits (" +
				"id INT NOT NULL, limitMin INT NOT NULL,limitMax INT NOT NULL, proc DOUBLE NOT NULL, PRIMARY KEY (id));" +
				"");

//		res = st.executeUpdate("INSERT INTO credits VALUES (0,10000,0.2);");
//		res = st.executeUpdate("INSERT INTO credits VALUES (1,13000,0.3);");
//		res = st.executeUpdate("INSERT INTO credits VALUES (2,20000,0.5);");
//		res = st.executeUpdate("INSERT INTO credits VALUES (3,5000,0.17);");

		res = st.executeUpdate("CREATE TABLE Banks (" +
				"id INT NOT NULL, id_cl INT NOT NULL, id_cr INT NOT NULL,PRIMARY KEY (id)," +
				"FOREIGN KEY (id_cl) REFERENCES clients(id)," +
				"FOREIGN KEY (id_cr) REFERENCES credits(id));" +
				"");

		res = st.executeUpdate("CREATE TABLE ofCredits (id INT NOT NULL, id_cl INT NOT NULL, id_cr INT NOT NULL, summa DOUBLE NOT NULL, month INT NOT NULL, " +
				"PRIMARY KEY (id));");

		res = st.executeUpdate("CREATE TABLE ras (" +
				"id INT NOT NULL, idOfCr INT NOT NULL, date VARCHAR(50) NOT NULL, summa DOUBLE NOT NULL, summaTelCr DOUBLE NOT NULL, summaPr DOUBLE NOT NULL" +
				",PRIMARY KEY (id));");





//		res = st.executeUpdate("INSERT INTO Banks VALUES (0," +
//				"(SELECT id FROM clients where name = 'Nik')," +
//				"(SELECT id FROM credits where limit = 10000));");


//		ResultSet result = st.executeQuery( "SELECT * FROM clients");
//		while (result.next()){
//			System.out.println(result.getInt("id")+" | "+
//					result.getString("surname")+" | "+
//					result.getString("name") +" | "+
//					result.getString("otchestvo")+" | "+
//					result.getInt("phonenumber") +" | "+
//					result.getString("mail")+" | "+
//					result.getInt("passportNumber"));
//		}
//
//		result = st.executeQuery( "SELECT * FROM credits");
//		while (result.next()){
//			System.out.println(result.getInt("id")+" | "+
//					result.getInt("limit")+" | "+
//					result.getDouble("proc"));
//		}

//		result = st.executeQuery( "SELECT * FROM banks");
//		while (result.next()){
//			System.out.println(result.getInt("id")+" | "+
//					result.getInt("id_cl")+" | "+
//					result.getInt("id_cr"));
//		}
	}

}

