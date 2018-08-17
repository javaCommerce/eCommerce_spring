package fr.adaming.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clients")

public class Client {

	/** Déclaration des attribus */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cl")

	private Long idClient;
	private String nomClient;
	private String adresse;
	private String email;
	private String tel;
	private String mdp;

	/** Déclaration de l'association uml en java */
	@OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
	private List<Commande> listeCommande;

	/** Declaration des constructeurs */

	public Client(Long idClient, String nomClient, String adresse, String email, String tel) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
	}

	public Client(String nomClient, String adresse, String email, String tel) {
		super();
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
	}

	public Client() {
		super();
	}

	public Client(List<Commande> listeCommande, String nomClient, String adresse, String email, String tel) {
		super();
		this.listeCommande = listeCommande;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
	}

	public Client(List<Commande> listeCommande, Long idClient, String nomClient, String adresse, String email,
			String tel) {
		super();
		this.listeCommande = listeCommande;
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
	}

	public Client(Long idClient, String nomClient, String adresse, String email, String tel, String mdp) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.mdp = mdp;
	}

	public Client(String nomClient, String adresse, String email, String tel, String mdp) {
		super();
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.mdp = mdp;
	}

	/** getter setter */

	public Long getIdClient() {
		return idClient;
	}

	public List<Commande> getListeCommande() {
		return listeCommande;
	}

	public void setListeCommande(List<Commande> listeCommande) {
		this.listeCommande = listeCommande;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
