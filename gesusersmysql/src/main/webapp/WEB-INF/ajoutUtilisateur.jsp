<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="inc/header.jsp"  %>
	<section>
	<h1 id="titre-principal">Ajout des utilisateurs</h1>
	
	<form method="post" class="main">
		<div class="formItem">
			<label>Nom</label>
			<input type="text" name="nom" value="${ utilisateur.nom }">
			<span class="erreur">${ erreurs.nom }</span>
		</div>
		
		<div class="formItem">
			<label>Prenom</label>
			<input type="text" name="prenom" value="${ utilisateur.prenom }">
			<span class="erreur">${ erreurs.prnom }</span>
		</div>	
		
		<div class="formItem">
			<label>Login</label>
			<input type="text" name="login" value="${ utilisateur.login }">
			<span class="erreur">${ erreurs.login }</span>
		</div>
		
		<div class="formItem">
			<label>Password</label>
			<input type="password" name="password" value="${ utilisateur.password }">
			<span class="erreur">${ erreurs.password }</span>
		</div>	
		
		<div class="formItem">
			<label>Confirmation de Password</label>
			<input type="password" name="passwordBis">
			<span class="erreur">${ erreurs.passwordBis }</span>
		</div>	
		
		<div class="formItem">
			<input type="submit" value="ajouter">
		</div>
	</form>
	</section>

<%@include file="inc/footer.jsp"  %>