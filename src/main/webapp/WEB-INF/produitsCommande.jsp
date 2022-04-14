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
<c:out value="Bonjour ${utilisateur.prenom } ${utilisateur.nom }"/></br>
<a href="deconnect">Déconnexion</a>
</br></br>
<h2><c:out value="Commande n° ${num }"/></h2>

<table border=2>
<tr><td>Produit</td><td>Image</td><td>Quantité</td></tr>
<c:forEach items="${produitsCommande }" var="produitCommande">
<tr><td>
<c:out value="${produitCommande[1] }"/>
</td><td>
<img src="${produitCommande[2] }" height="100px" width="auto"/>
</td><td>
<c:out value="${produitCommande[0] }"/>
</tr>
</c:forEach>
</table>
</br></br></br>
<a href="listeCommandes">Retour à la liste de commandes</a>
</body>
</html>