<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Ajout produit</title>
</head>
<body>
<div id="id1">
<c:out value="Bonjour ${utilisateur.prenom } ${utilisateur.nom }"/></br>
<a href="deconnect">Déconnexion</a>
</div>
</br></br>
<div id="id1">
<h2>Ajout de produit</h2>
<table >
<form action=ajout method=post>
<tr><td >Libelle :</td> 
<td ><input type=text name=libelle></td></tr>
<tr><td >Prix :</td> 
<td ><input type=text name=prix></td></tr>
<tr><td >Image :</td> 
<td ><input type=text name=image></td></tr>
<tr></tr>
<tr><td><button class="bouton" type=submit>Ajout</button></td></tr>
</form>
</table>
</br>
</div>

</br></br>
<table class="t1"><tr>
<c:set var="i" value="${0 }"/>
<c:forEach items="${produits }" var="produit" varStatus="status">
<td class="td1">
<img src="${produit.image }" height="100px" width="auto"/></br>
<c:out value="Libelle: ${produit.libelle }"/></br>
<c:out value="Prix: ${produit.prix } E"/></br>
<c:if test="${ i < chiffresAffaire.size()}">
<c:out value="Chiffre d'affaire: ${chiffresAffaire.get(i) }"/>
</c:if>
<c:set var="i" value="${i+1 }"/>
</td>
<c:if test="${status.count%5 == 0 }">
</tr><tr>
</c:if>
</c:forEach>
</table>
</br></br>
<c:out value="Le produit le plus rentable est : ${meilleurProduit.libelle }"/>

</br></br></br>
<a href="index.jsp">Retour à l'accueil</a>
</body>
</html>