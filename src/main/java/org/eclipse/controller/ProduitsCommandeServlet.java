package org.eclipse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.config.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Servlet implementation class ProduitsCommandeServlet
 */
public class ProduitsCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduitsCommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
				
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "select compr.quantite, prod.libelle, prod.image from CommandeProduit compr, Produit prod"
				+ " where compr.produit = prod.id and compr.commande.num =  :num";
		Query query = session.createQuery(hql);
		query.setParameter("num", num);
		List<Object[]> produitsCommande = (List<Object[]>) query.list();
		request.setAttribute("produitsCommande", produitsCommande);
		request.setAttribute("num", num);
		
		request.getRequestDispatcher("/WEB-INF/produitsCommande.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
