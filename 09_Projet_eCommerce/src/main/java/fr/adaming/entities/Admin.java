package fr.adaming.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * D�finition de la classe persistante Admin
 * @author inti0489
 *
 */

/**
 * Annotation pour rendre la classe persistante
 * Les attributs seront stock�s dans une table de la bd appel�e admins 
 * @author inti0489
 *
 */

@Entity
@Table(name = "admins")
public class Admin {

	/**
	 * D�claration des attributs
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ad")
	private int id;
	private String mail;
	private String mdp;

	/**
	 * D�claration des constructeurs
	 */
	public Admin() {
		super();
	}

	public Admin(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}

	public Admin(int id, String mail, String mdp) {
		super();
		this.id = id;
		this.mail = mail;
		this.mdp = mdp;
	}

	/**
	 * D�claration des getters et setters
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
