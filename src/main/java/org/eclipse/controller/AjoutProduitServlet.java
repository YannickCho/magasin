package org.eclipse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.config.HibernateUtil;
import org.eclipse.dao.ProduitDao;
import org.eclipse.dao.UtilisateurDao;
import org.eclipse.model.Produit;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Servlet implementation class AjoutProduitServlet
 */
public class AjoutProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjoutProduitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();

		ProduitDao produitDao = new ProduitDao(session);
		List<Produit> produits = produitDao.findAll();
		request.setAttribute("produits", produits);

		String hql = "select sum(compr.quantite)*pr.prix from Produit pr, CommandeProduit compr"
				+ " where pr.id = compr.produit group by pr.id order by pr.id";
		Query query = session.createQuery(hql);
		List<Double> chiffresAffaire = (List<Double>) query.list();
		request.setAttribute("chiffresAffaire", chiffresAffaire);
		if (chiffresAffaire.size() != 0) {
			double meilleurCA = 0L;
			int indMeilProd = 0;
			for (int i = 0; i < chiffresAffaire.size(); i++) {

				double CA = chiffresAffaire.get(i);
				if (CA > meilleurCA) {
					meilleurCA = CA;
					indMeilProd = i;
				}
			}
			Produit meilleurProduit = produits.get(indMeilProd);
			request.setAttribute("meilleurProduit", meilleurProduit);
		}
		request.getRequestDispatcher("/WEB-INF/ajoutProduit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String libelle = request.getParameter("libelle");
		double prix = Double.parseDouble(request.getParameter("prix"));
		String image = request.getParameter("image");
		Produit produit = new Produit();

		produit.setLibelle(libelle);
		produit.setPrix(prix);
		produit.setImage(image);

		Session session = HibernateUtil.getSessionFactory().openSession();		
		ProduitDao produitDao = new ProduitDao(session);
		int cle;
		try {
			cle = produitDao.save(produit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
