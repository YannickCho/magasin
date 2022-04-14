package org.eclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Produit{
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE)
	private int id;
	private String libelle;
	private double prix;
	private String image;


	@OneToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "produit", fetch = FetchType.EAGER) 
	@Fetch(FetchMode.SELECT)
	private List<CommandeProduit> commandesProduits = new ArrayList<CommandeProduit>();


	public Produit() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public List<CommandeProduit> getCommandesProduits() { 
		return	commandesProduits; 
	}

	public void setCommandesProduits(List<CommandeProduit> commandesProduits) {
		this.commandesProduits = commandesProduits; 
	}

	@Override
	public String toString() {
		return "id : " + id + "   libelle : " + libelle + "   prix : " + prix + "   image : " + image;
	}


	public void addCommandeProduit(CommandeProduit e) { 
		commandesProduits.add(e); 
		e.setProduit(this);
	}

	public void removeCommandeProduit(Object o) { 
		commandesProduits.remove(o);
	}



}
