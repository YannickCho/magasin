package org.eclipse.model;



import java.time.LocalDate;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Commande {
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE)
	private int num;
	private LocalDate date;

	@ManyToOne()
	@JoinColumn(name="utilisateur", referencedColumnName="id", nullable=false)
	private Utilisateur utilisateur;



	public Commande() {

	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}



	public Utilisateur getUtilisateur() { 
		return utilisateur; 
	}


	public void setUtilisateur(Utilisateur utilisateur) { 
		this.utilisateur = utilisateur; 
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}



}
