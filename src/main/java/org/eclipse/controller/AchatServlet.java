package org.eclipse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.config.HibernateUtil;
import org.eclipse.dao.ProduitDao;
import org.eclipse.model.Panier;
import org.eclipse.model.Produit;
import org.eclipse.model.Utilisateur;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Servlet implementation class AchatServlet
 */
public class AchatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AchatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Panier> listePa = new ArrayList<>();	
		Produit produit = new Produit();
		HttpSession httpSession = request.getSession();
		Utilisateur utilisateur = (Utilisateur)httpSession.getAttribute("utilisateur");

		listePa = (ArrayList<Panier>)httpSession.getAttribute("panier");
		Session session = HibernateUtil.getSessionFactory().openSession();

		ProduitDao produitDao = new ProduitDao(session);
		List<Produit> produits = produitDao.findAll();
		request.setAttribute("produits", produits);
		
		if (request.getParameter("id") != null)
			produit = session.get(Produit.class, Integer.parseInt(request.getParameter("id")));
		
		String hql1 = "select compr.produit from Commande com, CommandeProduit compr where com.num = compr.commande and "
				+ "com.utilisateur.id = :id";
		String hql2 ="select distinct u2.id from Utilisateur u1, Utilisateur u2, Commande com1, Commande com2, CommandeProduit compr1, CommandeProduit compr2 "
				  + "where u1.id = com1.utilisateur and u2.id = com2.utilisateur and com1.num = compr1.commande and com2.num = compr2.commande and "
				  + "compr1.produit = compr2.produit and u1.id = :id and u2.id <> :id"; 
		String hql3 = "select compr.produit.id from Commande com, CommandeProduit compr where com.num = compr.commande and com.utilisateur.id in (" + hql2 + ")";
		String hql = "from Produit pr where pr.id not in (" + hql1 + ") and pr.id in (" + hql3 + ")";
		Query query = session.createQuery(hql);
		query.setParameter("id", utilisateur.getId());
		List<Produit> produitsRecom = (List<Produit>) query.list();
		request.setAttribute("produitsRecom", produitsRecom);
		
		if (request.getParameter("act") != null) {
			if (request.getParameter("act").equals("ajout")) {
				listePa = ajouter(listePa, produit);
				httpSession.setAttribute("panier", listePa);
				request.getRequestDispatcher("/WEB-INF/achat.jsp").forward(request, response);
			}
			else if (request.getParameter("act").equals("ajout2")) {
				listePa = ajouter(listePa, produit);
				httpSession.setAttribute("panier", listePa);
				request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
			}
			else {
				listePa = enlever(listePa, produit);
				httpSession.setAttribute("panier", listePa);
				request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
			}
		}
		else 
			request.getRequestDispatcher("/WEB-INF/achat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public ArrayList<Panier> ajouter(ArrayList<Panier> listePa, Produit produit) {
		boolean dansPanier = false;
		Panier panier = new Panier();

		if(listePa == null) {
			listePa = new ArrayList<>();
			panier.setProduit(produit);
			panier.setQuantite(1);
			listePa.add(panier);
		}
		else {
			for (int i = 0; i < listePa.size(); i++) {
				panier = listePa.get(i);
				if (panier.getProduit().getId() == produit.getId()) {
					dansPanier = true;
					break;
				}
			}
			if (dansPanier) {
				panier.setQuantite(panier.getQuantite()+1);

			}
			else {
				panier = new Panier();
				panier.setProduit(produit);
				panier.setQuantite(1);
				listePa.add(panier);
			}
		}

		return listePa;
	}

	public ArrayList<Panier> enlever(ArrayList<Panier> listePa, Produit produit) {
		Panier panier = new Panier();

		for (int i = 0 ; i < listePa.size(); i++) {
			panier = listePa.get(i);
			if (panier.getProduit().getId() == produit.getId())
				break;
		}

		panier.setQuantite(panier.getQuantite()-1);
		if (panier.getQuantite() == 0)
			listePa.remove(panier);

		return listePa;
	}
}
