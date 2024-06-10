package servlets;

import java.io.IOException;

import beans.Utilisateur;
import dao.UtilisateurDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateUser extends HttpServlet
{
	
	private static final String VUE_UPDATE_UTILISATEUR = "/WEB-INF/editerUtilisateur.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id != null && id.matches("[0-9]+")) 
		{
			Utilisateur utilisateur = UtilisateurDao.get(Integer.parseInt(id));
			
			if (utilisateur != null) {
				req.setAttribute("utilisateur", utilisateur);
				getServletContext().getRequestDispatcher(VUE_UPDATE_UTILISATEUR).forward(req, resp);
				return;
			}
			
		}
		resp.encodeRedirectURL("list");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		Utilisateur utilisateur = new Utilisateur(Integer.parseInt(id), nom, prenom, login, password);
		
		UtilisateurDao.modifier(utilisateur);
		resp.sendRedirect("list");
	}
}
