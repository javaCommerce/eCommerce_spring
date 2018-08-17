package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.entities.Admin;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	/** D�claration des attributs vus de la facelet */
	private Client client;
	private Admin admin;
	private boolean indice;

	/** Transformation de l'association UML en Java */
	@ManagedProperty(value = "#{clService}")
	private IClientService clientService;

	@ManagedProperty(value = "#{coService}")
	private ICommandeService commandeService;

	/** Getters obligatoires pour l'injection de d�pendance */
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	public void setCommandeService(ICommandeService commandeService) {
		this.commandeService = commandeService;
	}

	/**
	 * M�thode qui se lance � l'instanciation du managedBean permettant de
	 * r�cup�rer l'admin
	 */

	@PostConstruct
	public void init() {
		admin = (Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aSession");
		this.indice = false;
	}

	/** Constructeur vide */
	public ClientManagedBean() {
		super();
		this.client = new Client();
		this.admin = new Admin();
	}

	/** Getters et setters */
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	/** M�thodes du managedBean */

	/** D�finition de la m�thode de connexion du client � son compte */

	public String loginClient() {

		/** V�rifier que le client existe */
		Client clientIn = clientService.isExist(this.client);

		if (clientIn != null) {
			/** ajouter le client trouv� dans la session http */
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clSession", clientIn);

			/** r�cup�rer la liste des commandes du client */
			List<Commande> listeCommande = commandeService.getAllCommande(client);

			/** ajouter la liste trouv� dans la session http */
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("coListe", listeCommande);

			return "accueilClient";
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Vos identifiants de connexion sont erron�s"));
		return "loginClient";
	}

	/** D�finition de la m�thode de d�connexion du client � son compte */

	public String logoutClient() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();

		return "loginClient";
	}

	/** D�finition des m�thodes du CRUD client */

	/** M�thode d'ajout d'un client */

	public String addClient() {

		/**
		 * Appel de la m�thode service pour ajouter le client
		 */
		Client clientAjout = clientService.addClient(this.client);

		if (clientAjout.getIdClient() != 0) {

			this.client = clientAjout;

			return "accueilClient";

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La cr�ation de votre compte a �chou� !"));
			return "accueilClient";

		}

	}

	/** M�thode de r�cup�ration d'un client par son ID */

	public String getClientById() {

		/** Appel de la m�thode getClientById de service */
		Client clientOut = clientService.getClientById(this.client);

		if (clientOut != null) {

			/**
			 * Stocker le client trouv� dans l'attribut du mb pour l'afficher
			 * sur la page
			 */
			this.client = clientOut;
			this.indice = true;

			return "rechercheClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ce client n'a pas encore de compte !"));
			this.indice = false;
			return "rechercheClient";
		}
	}

	/** M�thode de suppression d'un client */

	public String deleteClient() {

		/** Appel de la m�thode deleteClient de service */
		int delVerif = clientService.deleteClient(this.client);

		if (delVerif != 0) {
			/** r�cup�rer la liste des �tudiants du formateur */
			List<Client> listeClient = clientService.getAllClient();

			/** ajouter la liste trouv� dans la session http */
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clListe", listeClient);

			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression du compte client a �chou�!"));
			return "deleteClient";
		}

	}

	/** M�thode de modification d'un client */

	public String updateClient() {

		int clVerif = clientService.updateClient(this.client);

		if (clVerif != 0) {
			/**
			 * R�cup�rer la liste des clients qui existent pour la mettre � jour
			 */
			List<Client> listeClient = clientService.getAllClient();

			/**
			 * Ajout du client dans la session http
			 */
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clListe", listeClient);

			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vos informations de compte n'ont pas pu �tre modifi�es !"));
			return "updateClient";
		}
	}

}
