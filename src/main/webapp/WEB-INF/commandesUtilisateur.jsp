<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Commande</title>
</head>
<body>
<div id="id1">
<c:out value="Bonjour ${utilisateur.prenom } ${utilisateur.nom }"/></br>
<a href="deconnect">Déconnexion</a>
</div>
</br></br>

<h2><c:out value="Commandes de ${u.prenom } ${u.nom }"/></h2>
<table border=2>
<tr><td>Num</td><td>Date</td></tr>
<c:forEach items="${commandesUtilisateur }" var="commandeUtilisateur">
<tr><td>
<c:out value="${commandeUtilisateur.num }"/>
</td><td>
<c:out value="${commandeUtilisateur.date }"/>
</tr>
</c:forEach>
</table>
</br></br>
<c:out value="Chiffre d'affaire du client : ${chiffreAffaire.get(0) }"/>
</br></br>
Liste des amis
<c:forEach items="${amis }" var="ami">
<table >
<tr><td>
<c:out value="${ami }"/>
</td></tr>
</table>
</c:forEach>
</br></br></br>
<a href="listeCommandes">Retour à la liste de commandes</a>
</body>
</html>