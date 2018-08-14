package fr.adaming.entities;

public class Panier {

	/**
	 * Déclaration des attributs
	 */
	private Long idPanier;

	/**
	 * Déclaration des constructeurs
	 */

	public Panier() {
		super();
	}

	public Panier(Long idPanier) {
		super();
		this.idPanier = idPanier;
	}

	/**
	 * Déclaration des getters et setters
	 */

	public Long getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(Long idPanier) {
		this.idPanier = idPanier;
	}

}
