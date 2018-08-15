package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.entities.Admin;
import fr.adaming.entities.Client;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	/** Déclaration des attributs vus de la facelet */
	private Client client;
	private Admin admin;
	private boolean indice;

	/** Transformation de l'association UML en Java */
	@ManagedProperty(value = "#{clService}")
	private IClientService clientService;

	/** Getters obligatoires pour l'injection de dépendance */
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	/**
	 * Méthode qui se lance à l'instanciation du managedBean permettant de
	 * récupérer l'admin
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

	/** Méthodes du managedBean */

	public String addClient() {

		/**
		 * Appel de la méthode service pour ajouter le client
		 */
		Client clientAjout = clientService.addClient(this.client);

		if (clientAjout.getIdClient() != 0) {

			this.client = clientAjout;

			return "accueilClient";

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La création de votre compte a échoué !"));
			return "accueilClient";

		}

	}

	public String getClientById() {

		/** Appel de la méthode getClientById de service */
		Client clientOut = clientService.getClientById(this.client);

		if (clientOut != null) {

			/**
			 * Stocker le client trouvé dans l'attribut du mb pour l'afficher
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

	public String deleteClient() {

		/** Appel de la méthode deleteClient de service */
		int delVerif = clientService.deleteClient(this.client);

		if (delVerif != 0) {
			// récupérer la liste des étudiants du formateur
			List<Client> listeClient = clientService.getAllClient();

			// ajouter la liste trouvé dans la session http
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clListe", listeClient);

			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression du compte client a échoué!"));
			return "deleteClient";
		}

	}

	public String updateClient() {

		int clVerif = clientService.updateClient(this.client);

		if (clVerif != 0) {
			/**
			 * Récupérer la liste des clients qui existent pour la mettre à jour
			 */
			List<Client> listeClient = clientService.getAllClient();

			/**
			 * Ajout du client dans la session http
			 */
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clListe", listeClient);

			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vos informations de compte n'ont pas pu être modifiées !"));
			return "updateClient";
		}
	}

}
