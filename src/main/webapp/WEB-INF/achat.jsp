<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Achat</title>
</head>
<body>
<div id="id1">
<c:out value="Bonjour ${utilisateur.prenom } ${utilisateur.nom }"/></br>
<a href="deconnect">Déconnexion</a>
</div>
</br></br>
<c:set var="listeP" value="${sessionScope.panier }"/>
<c:set var="nbPr" value="0"/>
<c:forEach items="${listeP }" varStatus="status">
<c:set var="nbPr" value="${status.count }"/>
</c:forEach>
<p>Panier: <a href="panier"><c:out value="${nbPr }"/></a></p>

<table class="t1"><tr>
<c:forEach items="${produits }" var="produit" varStatus="status">
<td class="td1">
<img src="${produit.image }" height="100px" width="auto"/></br>
<c:out value="Libelle: ${produit.libelle }"/></br>
<c:out value="Prix: ${produit.prix } E"/></br>

<a href="achat?act=ajout&id=${produit.id }"><button>Ajouter</button></a>
</td>
<c:if test="${status.count%5 == 0 }">
</tr><tr>
</c:if>
</c:forEach>
</tr>
</table>
</br></br>
Produits recommandés : </br>
<table class="t1"><tr>
<c:forEach items="${produitsRecom }" var="produitRecom" varStatus="status">
<td class="td1">
<img src="${produitRecom.image }" height="100px" width="auto"/></br>
<c:out value="Libelle: ${produitRecom.libelle }"/></br>
<c:out value="Prix: ${produitRecom.prix } E"/>

</td>
<c:if test="${status.count%5 == 0 }">
</tr><tr>
</c:if>
</c:forEach>
</tr>
</table>
</br></br></br>
<div id="id1">
<a href="index.jsp">Retour à l'accueil</a>
</div>
</body>
</html>