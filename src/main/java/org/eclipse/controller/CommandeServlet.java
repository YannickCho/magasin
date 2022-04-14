package org.eclipse.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.config.HibernateUtil;
import org.eclipse.dao.CommandeProduitDao;
import org.eclipse.model.Commande;
import org.eclipse.model.CommandeProduit;
import org.eclipse.model.Panier;
import org.eclipse.model.Produit;
import org.eclipse.model.Utilisateur;
import org.hibernate.Session;

/**
 * Servlet implementation class CommandeServlet
 */
public class CommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		Commande commande = new Commande();
		Utilisateur utilisateur = (Utilisateur)httpSession.getAttribute("utilisateur");
		
		commande.setUtilisateur(utilisateur);
		commande.setDate(LocalDate.now());
		utilisateur.addCommande(commande);
				
		ArrayList<Panier> panier = (ArrayList<Panier>)httpSession.getAttribute("panier");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		CommandeProduitDao commandeProduitDao = new CommandeProduitDao(session);
		
		for (int i = 0; i < panier.size(); i++) {
			System.out.println("test"+i);
			Produit produit = panier.get(i).getProduit();
			int quantite = panier.get(i).getQuantite();
			CommandeProduit comProd = new CommandeProduit();
			comProd.setCommande(commande);
			comProd.setQuantite(quantite);
			produit.addCommandeProduit(comProd);
						
			try {
				commandeProduitDao.save(comProd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
									
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
