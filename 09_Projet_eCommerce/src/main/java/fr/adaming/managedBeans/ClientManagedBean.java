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

}
