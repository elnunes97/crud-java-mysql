package servlets;

import java.io.IOException;

import beans.Utilisateur;
import dao.UtilisateurDao;
import forms.AddUserForm;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddUser extends HttpServlet {

	private static final String VUE_AJOUT_UTILISATEUR = "/WEB-INF/ajoutUtilisateur.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR);
		dispatcher.forward(req, resp);
		
	}
	//@Override
	//protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String nom = req.getParameter("nom");
		//String prenom = req.getParameter("prenom");
		//String login = req.getParameter("login");
		//String password = req.getParameter("password");
		//Utilisateur utilisateur = new Utilisateur(nom, prenom, login, password);
		
		//UtilisateurDao.ajouter(utilisateur);
		//resp.sendRedirect("list");
	//}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AddUserForm form = new AddUserForm(req);
		
		if (form.ajouter()) {
			resp.sendRedirect("list");
		}
		else {
			req.setAttribute("status", form.getStatus());
			req.setAttribute("statusMessage", form.getStatusMessage());
			req.setAttribute("utilisateur", form.getUtilisateur());
			req.setAttribute("erreur", form.getErreurs());
			
			getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR).forward(req, resp);
		}	
	}
	
}
