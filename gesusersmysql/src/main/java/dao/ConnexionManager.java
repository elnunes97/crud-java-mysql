package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

//import com.mysql.cj.protocol.Resultset;

import beans.Utilisateur;

public class ConnexionManager {
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost/tp_jee", "nunes", "nunes");
			return connexion;
		}
		catch (Exception e) {
			return null;
			
		}
	}
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost/tp_jee", "nunes", "nunes");
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("select * from utilisateur");
			
			ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
			
			int id;
			String nom, prenom, login, password;
			while (resultat.next()) {
				id = resultat.getInt("id");
				nom = resultat.getString("nom");
				prenom = resultat.getString("prenom");
				login = resultat.getString("login");
				password = resultat.getString("password");
				
				utilisateurs.add(new Utilisateur(id, nom, prenom, login, password));
				
			}
			resultat.close();
			connexion.close();
			System.out.println("Liste des Utilisateurs");
			
			for (Utilisateur utilisateur : utilisateurs) {
				System.out.printf("ID 		: %d\n", utilisateur.getId());
				System.out.printf("NOM 		: %s\n", utilisateur.getNom());
				System.out.printf("PRENOM 	: %s\n", utilisateur.getPrenom());
				System.out.printf("LOGIN 	: %s\n", utilisateur.getLogin());
				System.out.printf("PASSWORD : %s\n", utilisateur.getPassword());
				System.out.printf("===========================================");
			}
			
		} 
		catch (ClassNotFoundException e) 
		{
			System.err.println("Erreur de chargement du Driver Myaql");

			e.printStackTrace();
		} catch (SQLException e) {
			System.err.print("Erreur d'execution de l requete : " + e.getMessage());
			
			e.printStackTrace();
		}
		
		
	

	}

}
