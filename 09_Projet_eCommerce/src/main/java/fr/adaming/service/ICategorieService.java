package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Categorie;

public interface ICategorieService {

	

	public Categorie addCategorie(Categorie cat);
	
	public int supprCategorie(Categorie cat);
	
	public int modifCategorie(Categorie cat);
	
	public Categorie getCategorieById(Categorie cat);
	
	public List<Categorie> getAllCategorie();
	
	
}
