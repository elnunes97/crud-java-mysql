package servlets;

import java.io.IOException;
import java.util.ArrayList;

import beans.Utilisateur;
import dao.UtilisateurDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/list", "/"})
public class ListUser extends HttpServlet {
	private static final String VUE_LIST_UTILISATEUR = "/WEB-INF/listerUtilisateurs.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR);
		ArrayList<Utilisateur> listeUtilisateurs = UtilisateurDao.lister();
		req.setAttribute("utilisateurs", listeUtilisateurs);
		dispatcher.forward(req, resp);
	}

}
