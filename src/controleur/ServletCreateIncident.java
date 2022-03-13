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
import entite.Incident;
import model.HeroRepository;
import model.IncidentRepository;
import model.TypeIncidentRepository;

@SuppressWarnings("serial")
@WebServlet("/inscrIncident")
public class ServletCreateIncident extends HttpServlet  {
	private TypeIncidentRepository typeIncidentRepository;
	private IncidentRepository incidentRepository;
	private HeroRepository heroRepository;
	public ServletCreateIncident() {
		typeIncidentRepository = new TypeIncidentRepository();
		heroRepository = new HeroRepository();
		incidentRepository = new IncidentRepository();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TypeIncident> typeIncidents = typeIncidentRepository.findAll();
		request.setAttribute("typeIncidents", typeIncidents);
		String urlVue = "createIncident.jsp";
		request.getRequestDispatcher(urlVue).forward(request, response); 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlVue = "createIncident.jsp";
		if(request.getParameter("createIncident") != null) {
//			double latitude = Double.parseDouble(request.getParameter("latitude")); 
//			double longitude = Double.parseDouble(request.getParameter("longitude")); 
			String ville = request.getParameter("ville");  
			GPSCoordinates gpsVille = GPSCoordinates.getGpsCoordinatesByAddress(ville);
			double latitude = gpsVille.getLatitude().doubleValue();
			double longitude = gpsVille.getLongitude().doubleValue();
			String typeIncident = request.getParameter("incidentType"); 
			Incident incident = new Incident(ville, latitude, longitude);
			
			incidentRepository.creer(incident, Integer.parseInt(typeIncident));
			
			ArrayList<Hero> heros = heroRepository.findHeroDispo(latitude, longitude, typeIncident);
			request.setAttribute("heros", heros);
			urlVue = "listHeroDispo.jsp";
		}
		

		request.getRequestDispatcher(urlVue).forward(request, response); 
	}
}
