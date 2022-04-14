package org.eclipse.dao;

import org.eclipse.model.Commande;
import org.hibernate.Session;

public class CommandeDao extends GenericDao<Commande,Integer>{

	public CommandeDao(Session session) {
		super(Commande.class, session);
	}

}
