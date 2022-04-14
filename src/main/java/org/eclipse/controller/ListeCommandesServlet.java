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
import org.eclipse.dao.CommandeDao;
import org.eclipse.dao.UtilisateurDao;
import org.eclipse.model.Commande;
import org.eclipse.model.Utilisateur;
import org.hibernate.Session;

/**
 * Servlet implementation class ListeCommandesServlet
 */
public class ListeCommandesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeCommandesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		Utilisateur utilisateur = (Utilisateur)httpSession.getAttribute("utilisateur");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		CommandeDao commandeDao = new CommandeDao(session);
		ArrayList<Commande> commandes = new ArrayList<>();
		
		if (utilisateur.isAdmin()) {
			commandes = (ArrayList<Commande>)commandeDao.findAll();
		}
		else {
			
			List<Commande> commandes2 = commandeDao.findAll();
			for (int i = 0; i < commandes2.size(); i++) {
				Commande commande = commandes2.get(i);
				if (commande.getUtilisateur().getId() == utilisateur.getId())
					commandes.add(commande);
			}
		}
		
		httpSession.setAttribute("commandes", commandes);
		
		request.getRequestDispatcher("/WEB-INF/listeCommandes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
