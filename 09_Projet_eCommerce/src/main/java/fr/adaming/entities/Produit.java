package fr.adaming.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Définition de la classe persistante Produit
 */

/**
 * Annotations pour rendre la classe persistante Créer une table produits pour
 * enregistrer les attributs
 */
@Entity
@Table(name = "produits")
public class Produit {

	/**
	 * Déclaration des attributs
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pro")
	private Long idProduit;

	private String designation;
	private String description;
	private double prix;
	private int quantite;
	@Transient
	private boolean selectionne;
	@Lob
	private byte[] photo;

	@Transient
	private String image;

	/**
	 * Transformation de l'association UML en Java
	 */

	@ManyToOne
	@JoinColumn(name = "cat_id", referencedColumnName = "id_cat")
	private Categorie cat;

	@OneToMany(mappedBy = "produit")
	List<LigneCommande> listeLigneCommande;

	/**
	 * Déclaration des constructeurs
	 */
	public Produit() {
		super();
	}

	public Produit(String designation, String description, double prix, int quantite, boolean selectionne,
			byte[] photo) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}

	public Produit(Long idProduit, String designation, String description, double prix, int quantite,
			boolean selectionne, byte[] photo) {
		super();
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}

	public Produit(String designation, String description, double prix, int quantite, boolean selectionne, byte[] photo,
			Categorie cat) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.cat = cat;
	}

	public Produit(Long idProduit, String designation, String description, double prix, int quantite,
			boolean selectionne, byte[] photo, Categorie cat) {
		super();
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.cat = cat;
	}	

	public Produit(String designation, String description, double prix, int quantite, String image, Categorie cat) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.image = image;
		this.cat = cat;
	}
	
	
	public Produit(String designation, String description, double prix, int quantite, byte[] photo, Categorie cat) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;
		this.cat = cat;
	}

	/**
	 * Déclaration des getters et setters
	 */

	public Long getIdProduit() {
		return idProduit;
	}

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
