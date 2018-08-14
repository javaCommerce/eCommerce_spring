package fr.adaming.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * D�finition de la classe persistante Commande
 * 
 * @author inti0489
 *
 */

/**
 * Annotations pour rendre la classe persistante Cr�er une table commandes pour
 * enregistrer les attributs
 */

@Entity
@Table(name = "commandes")
public class Commande {

	/**
	 * D�claration des attributs
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_co")
	private Long idCommande;
	private Date dateCommande;

	/**
	 * Transformation de l'association UML en Java
	 */
	@ManyToOne
	@JoinColumn(name = "cl_id", referencedColumnName = "id_cl")
	Client client;

	@OneToMany(mappedBy = "commande")
	List<LigneCommande> listeLigneCommande;

	/**
	 * D�claration des constructeurs
	 */
	public Commande() {
		super();
	}

	public Commande(Date dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}

	public Commande(Long idCommande, Date dateCommande) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
	}

	public Commande(Long idCommande, Date dateCommande, Client client) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.client = client;
	}

	public Commande(Date dateCommande, Client client) {
		super();
		this.dateCommande = dateCommande;
		this.client = client;
	}

	/**
	 * D�claration des getters et setters
	 */

	public Long getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
