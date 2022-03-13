package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MaConnection {
	private static String url = "jdbc:mysql://localhost:3308/projet4indiv";
	private static String user = "root";
	private static String passwd = "";
	private static Connection connect;
	public static Connection getInstance(){
		if(connect == null){
			try {
			      // Etape 1 - chargement du driver
			      Class.forName("com.mysql.jdbc.Driver"); /* va chercher le pilote adéquat */
			      // Etape 2 - récupérer la connexion 
			      connect = DriverManager.getConnection(url,user, passwd);
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Connexion Impossible" + e.getMessage());
			}
		}       
		return connect; 
	}
	
	
}

