package fr.adaming.entities;

public class Panier {

	/**
	 * D�claration des attributs
	 */
	private Long idPanier;

	/**
	 * D�claration des constructeurs
	 */

	public Panier() {
		super();
	}

	public Panier(Long idPanier) {
		super();
		this.idPanier = idPanier;
	}

	/**
	 * D�claration des getters et setters
	 */

	public Long getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(Long idPanier) {
		this.idPanier = idPanier;
	}

}
