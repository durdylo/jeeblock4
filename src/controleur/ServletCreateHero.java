package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controleur.GPSCoordinates;
import entite.Hero;
import entite.TypeIncident;
import model.HeroRepository;
import model.TypeIncidentRepository;

@SuppressWarnings("serial")
@WebServlet("/inscrHero")
public class ServletCreateHero extends HttpServlet {
	private HeroRepository heroRepository;
	private TypeIncidentRepository typeIncidentRepository;
	public ServletCreateHero() {
		heroRepository = new HeroRepository();
		typeIncidentRepository = new TypeIncidentRepository();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TypeIncident> typeIncidents = typeIncidentRepository.findAll();
		request.setAttribute("page", "createHero");
		request.setAttribute("typeIncidents", typeIncidents);
		System.out.println("doGet");
		String urlVue = "createHero.jsp";
		request.getRequestDispatcher(urlVue).forward(request, response); 

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		if(request.getParameter("createHero") != null) {
			System.out.println("createHero");
			String nom = request.getParameter("nom");  
			String tel = request.getParameter("tel"); 
			String adresse = request.getParameter("adresse"); 
			GPSCoordinates gpsHero = GPSCoordinates.getGpsCoordinatesByAddress(adresse);
//			double latitude = Double.parseDouble(request.getParameter("latitude")); 
//			double longitude = Double.parseDouble(request.getParameter("longitude")); 
			Hero hero = new Hero(nom, tel, adresse, gpsHero.getLatitude().doubleValue(), gpsHero.getLongitude().doubleValue());
			int idHero = heroRepository.creer(hero);
			for(int i = 1; i<=10; i++) {
				String incident = request.getParameter("incident".concat(String.valueOf(i))); 
				if(incident != null) {
					System.out.println(incident);
					heroRepository.insertTypeIncidents(idHero, Integer.parseInt(incident));
				}
			}		
		}
		doGet(request, response); 
	}
}
