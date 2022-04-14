package org.eclipse.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.model.Utilisateur;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * Servlet Filter implementation class Filter1
 */
public class Filter1 extends HttpFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public Filter1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession httpSession = req.getSession();
		String methode = req.getMethod();
		String chemin = req.getServletPath();
		Cookie[] cookies = req.getCookies();

		Utilisateur utilisateur = (Utilisateur)httpSession.getAttribute("utilisateur");
		
		if (utilisateur == null && cookies != null) {
			
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("loginMavenProduit")) {
					Configuration configuration = new Configuration().configure();
					SessionFactory sessionFactory = configuration.buildSessionFactory();
					Session session = sessionFactory.openSession();
					Transaction transaction = session.beginTransaction();

					Criteria criteria = session.createCriteria(Utilisateur.class);
					criteria = criteria.add(Restrictions.eq("login", cookies[i].getValue()));
					List<Utilisateur> utilisateurs = (List<Utilisateur>) criteria.list();
					for(Utilisateur user : utilisateurs) 
						utilisateur = user;
					httpSession.setAttribute("utilisateur", utilisateur);



					transaction.commit();
					session.close();
					sessionFactory.close();
				}
			}
		}

		if (utilisateur != null || chemin.equals("/") || chemin.equals("/index.jsp") || chemin.equals("/inscription") && methode.equals("POST") 
				|| chemin.equals("/connexion") && methode.equals("POST")) {
			chain.doFilter(request, response);
			
		}
		else
			res.sendRedirect(req.getContextPath());
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
