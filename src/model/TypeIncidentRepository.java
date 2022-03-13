package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import entite.TypeIncident;
import entite.Hero;

public class TypeIncidentRepository {
    private Connection connection;
    public TypeIncidentRepository() {
        connection = MaConnection.getInstance();
    }
    
    
    public ArrayList<TypeIncident> findAll() {
		ArrayList<TypeIncident> typeIncidents = new ArrayList<TypeIncident>(); 
		TypeIncident typeIncident;
		ResultSet result;
		try {
			Statement st = this.connection.createStatement();
			result = st.executeQuery("SELECT * FROM type_incident");
			while (result.next()) {
				typeIncident = new TypeIncident();
				typeIncident.setId(result.getLong("id"));
				typeIncident.setNom(result.getString("nom"));
				typeIncidents.add(typeIncident);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typeIncidents;
	}
    
    public Stack<String> findByHero(int idHero) {
    	Stack<String> arrayIds = new Stack<String>();
		ResultSet result;
		try {
			Statement st = this.connection.createStatement();
			result = st.executeQuery("SELECT * FROM hero_type_incident where id_hero =" + idHero);
			while (result.next()) {
				arrayIds.push(result.getString("id_type_incident"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayIds;
    }
}