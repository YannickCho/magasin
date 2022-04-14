package org.eclipse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.config.HibernateUtil;
import org.eclipse.dao.UtilisateurDao;
import org.eclipse.model.Commande;
import org.eclipse.model.Utilisateur;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Servlet implementation class CommandesUtilisateurServlet
 */
public class CommandesUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommandesUtilisateurServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));

		Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "from Commande c where c.utilisateur.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Commande> commandesUtilisateur = (List<Commande>) query.list();
		request.setAttribute("commandesUtilisateur", commandesUtilisateur);

		UtilisateurDao utilisateurDao = new UtilisateurDao(session);
		Utilisateur u = utilisateurDao.findById(id);
		request.setAttribute("u", u);

		hql = "select sum(compr.quantite*pr.prix) from Commande com, CommandeProduit compr, Produit pr where com.num = compr.commande"
				+ " and compr.produit = pr.id and com.utilisateur.id = :id group by com.utilisateur"; 
		query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Double> chiffreAffaire = (List<Double>) query.list();
		request.setAttribute("chiffreAffaire", chiffreAffaire);


		hql ="select distinct u2.login from Utilisateur u1, Utilisateur u2, Commande com1, Commande com2, CommandeProduit compr1, CommandeProduit compr2 "
				+
				"where u1.id = com1.utilisateur and u2.id = com2.utilisateur and com1.num = compr1.commande and com2.num = compr2.commande and "
				+ "compr1.produit = compr2.produit and u1.id = :id and u2.id <> :id"; 
		query = session.createQuery(hql); 
		query.setParameter("id", id);
		List<String> amis = (List<String>) query.list();
		request.setAttribute("amis", amis);
		
		request.getRequestDispatcher("/WEB-INF/commandesUtilisateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
