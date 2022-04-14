<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Panier</title>
</head>
<body>
<div id="id1">
<c:out value="Bonjour ${utilisateur.prenom } ${utilisateur.nom }"/></br>
<a href="deconnect">Déconnexion</a>
</div>
</br></br>
<c:set var="listeP" value="${sessionScope.panier }"/>
<c:set var="somme" value="0"/>
<table border="2">
<tr><td>Quantité</td><td>Libelle</td><td>Prix</td><td>Photo</td></tr>

<c:forEach items="${listeP }" var="panier">
<c:set var="produit" value="${panier.produit }"/>
<c:set var="somme" value="${somme+panier.quantite*produit.prix }"/>
<tr>
<td><c:out value="${panier.quantite }"/></td>
<td><c:out value="${produit.libelle }"/></td>
<td><c:out value="${produit.prix }"/></td>
<td><img src="${produit.image }" height="100px" width="auto"/></td>
<td><a href="achat?act=supp&id=${produit.id }"><button>-</button></a></td>
<td><a href="achat?act=ajout2&id=${produit.id }"><button>+</button></a></td>

</tr>
</c:forEach>

</table>
</br>
<c:out value="Somme à payer : ${somme }"/> 
</br></br>
<form action="commande" method=post>
<button type=submit>Passer la commande</button>
</form>
</br></br></br>
<a href="achat">Retour au catalogue</a>
</body>
</html>