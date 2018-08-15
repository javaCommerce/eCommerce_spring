package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

public interface IProduitService {
	
/**Déclaration des méthodes de produit service*/
	
	public Produit addProduit(Produit p, Categorie cat);	
	
	public int supprProduit(Produit p, Categorie cat);
	
	public int modifPoduit(Produit p, Categorie cat);
	
	public List<Produit> getAllProduit(Categorie cat);
	
	public Produit getProduitById (Produit p, Categorie cat);
	

}
