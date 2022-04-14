package org.eclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Utilisateur {

	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE)
	private int id;
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private String mail;
	private boolean admin;


	@OneToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "utilisateur",fetch = FetchType.EAGER) 
	@Fetch(FetchMode.SELECT)
	private List<Commande> commandes = new ArrayList<Commande>();


	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Commande> getCommandes() { 
		return commandes; 
	}


	public void setCommandes(List<Commande> commandes) { 
		this.commandes = commandes; 
	}



	@Override
	public String toString() {
		return "login : " + login + "   password : " + password + "   nom : " + nom + "   prenom : " + prenom
				+ "   mail : " + mail;
	}



	public void addCommande(Commande e) { 
		commandes.add(e); 
		e.setUtilisateur(this);
	}


	public void removeCommande(Commande o) { 
		commandes.remove(o); 
		
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}



}
