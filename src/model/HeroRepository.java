package model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Stack;

import controleur.GPSCoordinates;
import entite.Hero;
import entite.Hero;

public class HeroRepository {
    private Connection connection;
    private GPSCoordinates gps;
    private TypeIncidentRepository typeIncidentRepository;
    public HeroRepository() {
        connection = MaConnection.getInstance();
		typeIncidentRepository = new TypeIncidentRepository();
		gps = new GPSCoordinates();
    }
    public int creer(Hero hero) {
    	int id=0;
        try {
            PreparedStatement prepare = this.connection.prepareStatement(
                          "INSERT INTO hero (nom, adresse, telephone, latitude, longitude) "+
                          "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                      );
            prepare.setString(1, hero.getNom());
            prepare.setString(2, hero.getAdresse());
            prepare.setString(3, hero.getTelephone());
            prepare.setDouble(4, hero.getLatitude());
            prepare.setDouble(5, hero.getLongitude());
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
    
    public void insertTypeIncidents (int idHero, int idTypeIncident) {
    	 try {
             PreparedStatement prepare = this.connection.prepareStatement(
                           "INSERT INTO hero_type_incident (id_hero, id_type_incident) "+
                           "VALUES (?, ?)"
                       );
            
             prepare.setInt(1, idHero);
             prepare.setInt(2, idTypeIncident);
             prepare.executeUpdate();
             prepare.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }
    
    public ArrayList<Hero> findAll() {
		ArrayList<Hero> heros = new ArrayList<Hero>(); 
		Hero hero;
		ResultSet result;
		try {
			Statement st = this.connection.createStatement();
			result = st.executeQuery("SELECT * FROM hero");
			while (result.next()) {
				hero = new Hero();
				hero.setId(result.getLong("id"));
				hero.setNom(result.getString("nom"));
				hero.setTelephone(result.getString("telephone"));
				hero.setAdresse(result.getString("adresse"));
				hero.setLatitude(result.getDouble("latitude"));
				hero.setLongitude(result.getDouble("longitude"));
				
				heros.add(hero);
			}
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return heros;
	}
    
    
    public ArrayList<Hero> findHeroDispo(double latitude, double longitude, String typeIncident) {
		ArrayList<Hero> heros = new ArrayList<Hero>(); 
		Hero hero;
		ResultSet result;
		
		try {
			Statement st = this.connection.createStatement();
			result = st.executeQuery("SELECT * FROM hero");
			while (result.next()) {
				hero = new Hero();
				hero.setId(result.getLong("id"));
				Stack<String> arrayIds = typeIncidentRepository.findByHero((int) result.getLong("id"));
				hero.setNom(result.getString("nom"));
				hero.setTelephone(result.getString("telephone"));
				hero.setAdresse(result.getString("adresse"));
				hero.setLatitude(result.getDouble("latitude"));
				hero.setLongitude(result.getDouble("longitude"));	
				double distance = gps.calculateDistance(new BigDecimal(hero.getLatitude(), MathContext.DECIMAL64), new BigDecimal(hero.getLongitude(), MathContext.DECIMAL64), new BigDecimal(latitude, MathContext.DECIMAL64), new BigDecimal(longitude, MathContext.DECIMAL64) );
				
				System.out.println(distance);
				System.out.println(arrayIds.contains(typeIncident));
			
				if(distance < 10000 && arrayIds.contains(typeIncident)) {
					heros.add(hero);
				}
				
			}
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return heros;
	}
   
}