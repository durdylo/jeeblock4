package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entite.Incident;

public class IncidentRepository {
	 private Connection connection;
	    public IncidentRepository() {
	        connection = MaConnection.getInstance();
	    }
	    
	    public int creer(Incident incident, int idTypeIncident) {
	    	int id=0;
	        try {
	            PreparedStatement prepare = this.connection.prepareStatement(
	                          "INSERT INTO incident (ville, latitude, longitude, id_type_incident) "+
	                          "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
	                      );
	            prepare.setString(1, incident.getVille());
	            prepare.setDouble(2, incident.getLatitude());
	            prepare.setDouble(3, incident.getLongitude());
	            prepare.setInt(4, idTypeIncident);
	            prepare.executeUpdate();
	            
	            ResultSet rs=prepare.getGeneratedKeys();
	            if(rs.next()){
	        		id=rs.getInt(1);
	        		}
	            prepare.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return id;
	    }
}
