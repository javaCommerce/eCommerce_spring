package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

public interface IProduitDao {
	
	/**D�claration des m�thodes de produit dao*/
	
	public Produit addProduit(Produit p);	
	
	public int supprProduit(Produit p);
	
	public int modifProduit(Produit p);
	
	public List<Produit> getAllProduit(Categorie cat);
	
	public Produit getProduitById (Produit p);
	
	
	

}
