package org.eclipse.dao;

import org.eclipse.model.Utilisateur;
import org.hibernate.Session;

public class UtilisateurDao extends GenericDao<Utilisateur,Integer> {

	public UtilisateurDao(Session session) {
		super(Utilisateur.class, session);
	}

}
