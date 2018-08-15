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
import fr.adaming.entities.Commande;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;

@ManagedBean(name = "coMB")
@RequestScoped
public class CommandeManagedBean implements Serializable {

	/** Déclaration des attributs vus de la facelet */
	private Commande commande;
	private Client client;
	private Admin admin;
	private boolean indice;

	/** Transformation de l'association UML en Java */
	@ManagedProperty(value = "#{coService}")
	private ICommandeService commandeService;

	@ManagedProperty(value = "#{clService}")
	private IClientService clientService;

	/** Setters obligatoires pour l'injection de dépendances */
	public void setCommandeService(ICommandeService commandeService) {
		this.commandeService = commandeService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	@PostConstruct
	public void init() {
		admin = (Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aSession");
		this.indice = false;
	}

	/** Constructeur vide */
	public CommandeManagedBean() {
		super();
		this.commande = new Commande();
		this.client = new Client();
	}

	/** Getters et setters */
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	public String addCommande() {

		Commande comAjout = commandeService.addCommande(this.commande, this.client);

		if (comAjout != null) {

			/**
			 * Stocker la commande ajoutée à l'attribut du mb pour l'afficher
			 * sur la facelet
			 */
			this.commande = comAjout;

			return "accueilClient";

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Votre commande n'a pas pu être ajouté !"));
			return "ajoutCommande";

		}

	}

	public String getCommandeById() {

		/** Appel de la méthode getCommandeById de service */
		Commande commandeGet = commandeService.getCommandeById(this.commande, this.client);

		if (commandeGet != null) {

			this.commande = commandeGet;
			this.indice = true;

			return "rechercheCommande";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cette commande n'existe pas !"));
			this.indice = false;
			return "rechercheCommande";
		}
	}

	public String deleteCommande() {

		/** Appel de la méthode deleteCommande de service */
		int delCoVerif = commandeService.deleteCommande(this.commande, this.client);

		if (delCoVerif != 0) {
			// récupérer la liste des étudiants du formateur
			List<Commande> listeCommande = commandeService.getAllCommande(client);

			// ajouter la liste trouvé dans la session http
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clListe", listeCommande);

			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression de votre commande a échoué!"));
			return "deleteCommande";
		}
	}

	public String updateCommande() {
		/** Appel de la méthode updateCommande de service */
		int updateCoVerif = commandeService.updateCommande(this.commande, this.client);

		if (updateCoVerif != 0) {
			// récupérer la liste des étudiants du formateur
			List<Commande> listeCommande = commandeService.getAllCommande(client);

			// ajouter la liste trouvé dans la session http
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clListe", listeCommande);

			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La modification de votre commande a échoué!"));
			return "updateCommande";
		}
	}

}
