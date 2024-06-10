package dao;

import java.io.ObjectInputFilter.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;




import com.mysql.cj.protocol.Resultset;

import beans.Utilisateur;

public class UtilisateurDao {
	
	private static int LastId = 0;
	
	private final static ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	

	
	public static ArrayList<Utilisateur> lister() {
		ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		try {
			Connection connexion = ConnexionManager.getConnection();
			Statement requete = connexion.createStatement();
			ResultSet resultat = requete.executeQuery("SELECT * FROM utilisateur");
			
			int id;
			String nom, prenom, login, password;
			
			while (resultat.next()) 
			{
				id = resultat.getInt("id");
				nom = resultat.getString("nom");
				prenom = resultat.getString("prenom");
				login = resultat.getString("login");
				password = resultat.getString("password");
				
				utilisateurs.add(new Utilisateur(id, nom, prenom, login, password));
				
			}
			resultat.close();
			connexion.close();
		}
		catch (Exception e) {
			System.err.println("Erreur durant la recuperation de la liste des utilisateur : " + e.getMessage());
			
		}
		return utilisateurs;
	}
	/////////
	
	
	 public static boolean ajouter( Utilisateur utilisateur) 
	 {
		try 
		{
			Connection connexion = ConnexionManager.getConnection();
			String requete = "insert into utilisateur(nom, prenom, login, password) values (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, utilisateur.getNom());
			preparedStatement.setString(2, utilisateur.getPrenom());
			preparedStatement.setString(3, utilisateur.getLogin());
			preparedStatement.setString(4, utilisateur.getPassword());
			
			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows == 1) 
			{
				return true;
			}
			preparedStatement.close();
			connexion.close();
		} 
		catch (Exception e) 
		{
			System.err.println("Erreur durant l'insertion de l'utilisateur");
		}
		return false;
	}
////////////////////
	public static boolean supprimer(int id) {
		try {
			Connection connexion = ConnexionManager.getConnection();
			String requete = "delete FROM utilisateur where id = ?";
			PreparedStatement preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, id);
			int deleteRows = preparedStatement.executeUpdate();
			
			
			if (deleteRows == 1) 
			{
				 return true;
				
			}
			preparedStatement.close();
			connexion.close();
		}
		catch (Exception e) {
			System.err.println("Erreur de la suppression de  l'utilisateur N°: " + id);
			 
		}
		return false; 
	}
///////////////////////////////////////////////////login
	public static boolean valider(String login, String password) 
	{
//Utilisateur utilisateurs = null;
		boolean status = false;
		
	try {
			Connection connexion = ConnexionManager.getConnection();
			PreparedStatement requete = connexion.prepareStatement(" select login from utilisateur where login = ? and password = ? ");
			requete.setString(1, login);
			requete.setString(2, password);
			
           
			ResultSet resultat = requete.executeQuery();
			
			
			status = resultat.next();
		
		
		resultat.close();
		connexion.close();
	}
	catch (Exception e) {
		System.err.println("Erreur durant la recuperation de  l'utilisateur : " + e.getMessage());
			
	}
		return status; 
		
	}
	
	
	
///////////////////////////////
	public static Utilisateur get(int id) 
	{
Utilisateur utilisateurs = null;
		
		try {
			Connection connexion = ConnexionManager.getConnection();
			PreparedStatement requete = connexion.prepareStatement("SELECT * FROM utilisateur where id = ?");
			requete.setInt(1, id);
			ResultSet resultat = requete.executeQuery();
			
			String nom, prenom, login, password;
			
			if (resultat.next()) 
			{
				id = resultat.getInt("id");
				nom = resultat.getString("nom");
				prenom = resultat.getString("prenom");
				login = resultat.getString("login");
				password = resultat.getString("password");
				
				utilisateurs = new Utilisateur(id, nom, prenom, login, password);
				
			}
			resultat.close();
			connexion.close();
		}
		catch (Exception e) {
			System.err.println("Erreur durant la recuperation de  l'utilisateur : " + e.getMessage());
			
		}
		return utilisateurs; 
		
	}

	public static boolean modifier(Utilisateur utilisateur) {
		try 
		{
			Connection connexion = ConnexionManager.getConnection();
			String requete = "update utilisateur set nom = ?, prenom = ?, login = ?, password = ? where id = ?";
			PreparedStatement preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, utilisateur.getNom());
			preparedStatement.setString(2, utilisateur.getPrenom());
			preparedStatement.setString(3, utilisateur.getLogin());
			preparedStatement.setString(4, utilisateur.getPassword());
			preparedStatement.setInt(5, utilisateur.getId());
			
			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 1) 
			{
				return true;
			} 
			preparedStatement.close();
			connexion.close();
		} 
		catch (Exception e) 
		{
			System.err.println("Erreur durant la mise a jours de l'utilisateur");
		}
		return false;
		
	}

}
