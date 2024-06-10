package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import dao.UtilisateurDao;
//import forms.LoginForm;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class AuthenticationManager extends HttpServlet {
	private static final String VUE_CONNEXION = "/WEB-INF/connexion";
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		getServletContext().getRequestDispatcher(VUE_CONNEXION).forward(request, response);
		
//	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (UtilisateurDao.valider(login, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", login);
            session.setMaxInactiveInterval(30*60); // temps de connexion en segondes pendant inativite
            Cookie userName = new Cookie("utilisateur", login);
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            response.sendRedirect("list");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(VUE_CONNEXION);
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }
    }
	
	

}
