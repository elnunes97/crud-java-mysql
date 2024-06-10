<%@page import="beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="inc/header.jsp"  %>
	<section>
<h1 id="titre-principal">Modifier utilisateur</h1>
<% Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur"); %>

<form method="post" class="main">
	<div class="formItem">
		<label>Nom</label>
		<input type="text" name="nom" value="<%= utilisateur.getNom() %>">
	</div>
	<div class="formItem">
		<label>Prenom</label>
		<input type="text" name="prenom" value="<%= utilisateur.getPrenom() %>">
	</div>
	<div class="formItem">		
		<label>Login</label>
		<input type="text" name="login" value="<%= utilisateur.getLogin() %>">
	</div>	
	<div class="formItem">	
		<label>Password</label>
		<input type="password" name="password" value="<%= utilisateur.getPassword() %>">
	</div>
	<div class="formItem">		
		<input type="submit" value="Modifier">
	</div>
	</form>
	</section>

<%@include file="inc/footer.jsp"  %>