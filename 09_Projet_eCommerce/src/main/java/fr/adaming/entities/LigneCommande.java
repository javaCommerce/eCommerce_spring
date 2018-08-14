package fr.adaming.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ligneCommandes")

public class LigneCommande {

	/** Decalartion de l'association uml en java */

	@ManyToOne
	@JoinColumn(name = "pro_id", referencedColumnName = "id_pro")
	private Produit produit;

	@ManyToOne
	@JoinColumn(name = "co_id", referencedColumnName = "id_co")
	private Commande commande;

	/** Declaration des attribus */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Lco")

	private Long idLco;
	private int quantite;
	private int prix;

	/** Declaration des constructeurs */

	public LigneCommande(Long idLco, int quantite, int prix) {
		super();
		this.idLco = idLco;
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(Produit produit, Commande commande, int quantite, int prix) {
		super();
		this.produit = produit;
		this.commande = commande;
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(Produit produit, Commande commande, Long idLco, int quantite, int prix) {
		super();
		this.produit = produit;
		this.commande = commande;
		this.idLco = idLco;
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(int quantite, int prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande() {
		super();
	}

	/** Declaration des getter et setter */

	public Long getIdLco() {
		return idLco;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public void setIdLco(Long idLco) {
		this.idLco = idLco;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

}
