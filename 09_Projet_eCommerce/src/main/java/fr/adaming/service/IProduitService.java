package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

public interface IProduitService {
	
/**Déclaration des méthodes de produit service*/
	
	public Produit addProduit(Produit p, Categorie cat);	
	
	public int supprProduit(Produit p, Categorie cat);
	
	public int modifProduit(Produit p, Categorie cat);
	
	public List<Produit> getAllProduit();
	
	public Produit getProduitById (Produit p);
	

}
