package org.eclipse.dao;

import org.eclipse.model.Produit;
import org.hibernate.Session;

public class ProduitDao extends GenericDao<Produit, Integer>{

	public ProduitDao(Session session) {
		super(Produit.class, session);
	}

}
