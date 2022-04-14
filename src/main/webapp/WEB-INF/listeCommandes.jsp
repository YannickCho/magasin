<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@ page isELIgnored="false" %>    
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Commandes</title>
</head>
<body>
<div id="id1">
<c:out value="Bonjour ${utilisateur.prenom } ${utilisateur.nom }"/></br>
<a href="deconnect">Déconnexion</a>
</div>
</br></br>

<table border=2>
<tr><td>Commande</td><td>Date</td><td>Client</td></tr>
<c:forEach items="${commandes }" var="commande">
<tr><td>
<a href="produitsCommande?num=${commande.num }"><c:out value="${commande.num }"/></a>
</td><td>
<c:out value="${commande.date }"/>
</td><td>
<a href="commandesUtilisateur?id=${commande.utilisateur.id }"><c:out value="${commande.utilisateur.login }"/></a>
</td>
</tr>
</c:forEach>
</table>

</br></br></br>
<a href="index.jsp">Retour à l'accueil</a>
</body>
</html>