package org.eclipse.model;




public class Panier {
	private Produit produit;
	private int quantite;	
	
	public Panier() {
		// TODO Auto-generated constructor stub
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		return "produit : " + produit + "   quantite : " + quantite;
	}
	
	

}
