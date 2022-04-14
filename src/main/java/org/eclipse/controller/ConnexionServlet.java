package org.eclipse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.config.HibernateUtil;
import org.eclipse.dao.UtilisateurDao;
import org.eclipse.model.Utilisateur;
import org.hibernate.Session;

/**
 * Servlet implementation class ConnexionServlet
 */
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionServlet() {
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
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		HttpSession httpSession = request.getSession();

		Session session = HibernateUtil.getSessionFactory().openSession();
		UtilisateurDao utilisateurDao = new UtilisateurDao(session);
		List<Utilisateur> utilisateurs = utilisateurDao.findAll();
		for (Utilisateur utilisateur : utilisateurs) 
			if (utilisateur.getLogin().equals(login) && utilisateur.getPassword().equals(pwd)) {
				httpSession.setAttribute("utilisateur", utilisateur);
				Cookie cookie = new Cookie("loginMavenProduit", login);
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
				request.setAttribute("estUtilisateur", "true");
			}
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
