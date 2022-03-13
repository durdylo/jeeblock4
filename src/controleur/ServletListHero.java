package controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entite.Hero;
import entite.TypeIncident;
import model.HeroRepository;
import model.TypeIncidentRepository;

@SuppressWarnings("serial")
public class ServletListHero  extends HttpServlet {
	private HeroRepository heroRepository;
	
	public ServletListHero() {
		heroRepository = new HeroRepository();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page", "listHero");
		ArrayList<Hero> heros = heroRepository.findAll();
		request.setAttribute("heros", heros);
		System.out.println("doGet");
		String urlVue = "listHeroDispo.jsp";
		request.getRequestDispatcher(urlVue).forward(request, response); 
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		String urlVue = "listHeroDispo.jsp";
		request.getRequestDispatcher(urlVue).forward(request, response); 
	}
}
