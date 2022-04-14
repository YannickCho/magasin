<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:choose>
<c:when test="${utilisateur != null }">
<div id="id1">
<c:out value="Bonjour ${utilisateur.prenom } ${utilisateur.nom }"/></br>
<a href="deconnect">Déconnexion</a>
</br></br>
</div>
</br>
<c:if test="${utilisateur.admin == 'true' }">
<a href="ajout"><button class="bouton">Ajouter un produit</button></a>
</br></br>
</c:if>
<a href="achat"><button class="bouton">Acheter</button></a>
</br></br>
<a href="listeCommandes"><button class="bouton">Liste des commandes</button></a>
</c:when>
<c:otherwise>
<h1>Authentification</h1>

<table>
<form action=connexion method=post>
<tr><td>Login :</td> 
<td><input type=text name=login></td></tr>
<tr><td>Mot de passe :</td> 
<td><input type=password name=pwd></td></tr>
<tr><td><button class="bouton" type=submit>Connexion</button></td></tr>
</form>
</table>
<br><br>
<h1>Inscription</h1>
<table>
<form action=inscription method=post>
<tr><td>Nom :</td> 
<td><input type=text name=nom></td></tr>
<tr><td>Prénom :</td> 
<td><input type=text name=prenom></td></tr>
<tr><td>Mail : </td>
<td><input type=text name=mail></td></tr>
<tr><td>Login :</td> 
<td><input type=text name=login></td></tr>
<tr><td>Mot de passe :</td> 
<td><input type=password name=pwd></td></tr>
<tr><td><button class="bouton" type=submit>Inscription</button></td></tr>
</form>
</table>
</c:otherwise>
</c:choose>
</body>
</html>