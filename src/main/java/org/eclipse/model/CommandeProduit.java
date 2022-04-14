package org.eclipse.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CommandeProduit {
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE)
	private int num;


	@ManyToOne(cascade= {CascadeType.ALL})	  
	@JoinColumn(name="commande", referencedColumnName="num", nullable=false)
	private Commande commande;

	@ManyToOne()	  
	@JoinColumn(name="produit", referencedColumnName="id")
	private Produit produit;
	
	
	private int quantite;


	public CommandeProduit() {

	}


	public Commande getCommande() { 
		return commande; 
	}

	public void setCommande(Commande commande) {
		this.commande = commande;

	}


	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public Produit getProduit() {
		return produit;
	}


	public void setProduit(Produit produit) {
		this.produit = produit;
		//produit.addCommandeProduit(this);
	}


	@Override public String toString() { return "commande : " + commande +
			"   quantite : " + quantite; }


}
