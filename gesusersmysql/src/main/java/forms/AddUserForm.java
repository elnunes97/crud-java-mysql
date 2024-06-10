package forms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import beans.Utilisateur;
import dao.UtilisateurDao;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public class AddUserForm {

	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_PASSWORD = "password";
	private static final String CHAMP_PASSWOR_BIS = "passwordBis";


	private Map<String, String> erreurs;
	private String statusMessage;
	private boolean status;
	private HttpServletRequest request;
	public Utilisateur  utilisateur; 
	{
		
	}
	
	public AddUserForm(HttpServletRequest request) {
		this.request = request;
		this.erreurs = new HashMap<String, String>();
	
		
	}
	
	public boolean ajouter() {
		String nom = this.getParameter(CHAMP_NOM);
		String prenom = this.getParameter(CHAMP_PRENOM);
		String login = this.getParameter(CHAMP_LOGIN);
		String password = this.getParameter(CHAMP_PASSWORD);
		this.utilisateur = new Utilisateur(nom, prenom, login, password);
		this.statusMessage = "echec de l'ajout";
		this.status = false;
		
		this.validerChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN, CHAMP_PASSWORD, CHAMP_PASSWOR_BIS);
		this.validerPassword();
		
		if (this.erreurs.isEmpty()) {
			if (UtilisateurDao.ajouter(utilisateur)) {
				this.statusMessage = "ajout effectue avec succes";
				this.status =true;
			}	
		}
		return this.status;
	}
	
	private String getParameter(String parametre) {
		String valeur = this.request.getParameter(parametre);
		
		if (valeur == null || valeur.isBlank()) {
			return null;
		}
		return valeur.trim();
	}
	
	private void validerChamps(String...  champs) {
		for (String champ : champs) {
			if (this.getParameter(champ) == null) {
				this.erreurs.put(champ, "les champ ne peut pas etre vide");
				
			}
		}
		
	}
	
	private void validerPassword() {
		String password = this.getParameter(CHAMP_PASSWORD);
		String passwordBis = this.getParameter(CHAMP_PASSWOR_BIS);
		
		if (password != null && !password.equals(passwordBis)) {
			this.erreurs.put(password, "les motes de passes ne sont pas conforme");
			
		}
		
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public boolean getStatus() {
		return status;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


}

