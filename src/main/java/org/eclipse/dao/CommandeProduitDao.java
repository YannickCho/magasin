package org.eclipse.dao;

import org.eclipse.model.CommandeProduit;
import org.hibernate.Session;

public class CommandeProduitDao extends GenericDao<CommandeProduit, Integer>{

	public CommandeProduitDao(Session session) {
		super(CommandeProduit.class, session);
	}

}
