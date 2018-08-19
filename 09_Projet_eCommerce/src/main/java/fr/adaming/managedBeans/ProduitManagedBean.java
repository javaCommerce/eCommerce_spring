package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import fr.adaming.entities.Admin;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "pMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	/** Déclaration des attribus */
	private Produit produit;
	private Categorie categorie;
	private Admin admin;
	private boolean indice;
	private List<Produit> listeP;
	private UploadedFile file;

	/** Transformation de l'association uml en java */
	@ManagedProperty("#{pService}")
	private IProduitService produitService;
	
	@ManagedProperty("#{catService}")
	private ICategorieService categorieService;

	/** Setter pour l'injection dépendance */
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	/** Méthode postConstruct */
	@PostConstruct
	public void init() {
		
		categorie = (Categorie) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("catSession");	
		
		/**Récupérer la liste*/		
		List<Produit> listeProduit=produitService.getAllProduit();
		
		/**Mettre la liste dans la session*/
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitListe", listeProduit);
		
		
		
		
	}

	/** Constructeur vide */
	public ProduitManagedBean() {
		super();
		this.produit = new Produit();
		this.categorie = new Categorie();
		this.admin = new Admin();
		
		
		
	}

	/** Getter et setter */

	
	public Produit getProduit() {
		return produit;
	}

	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Produit> getListeP() {
		return listeP;
	}

	public void setListeP(List<Produit> listeP) {
		this.listeP = listeP;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
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

	public IProduitService getProduitService() {
		return produitService;
	}

	
	
	/** Déclaration des méthodes */
	
	public String ajouterProduit(){
		
		
		Produit pIn = produitService.addProduit(this.produit, this.categorie);
		
		if(pIn!=null){
			
			List<Produit> listeProduit=produitService.getAllProduit();
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitListe", listeProduit);
		
		return "accueil";
		
		}else{
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas été effectué"));
			return "ajoutProduit";
		}		
	}
	
	
	public String rechercheProduit(){
		
		Produit pCherche = produitService.getProduitById(this.produit);
		
		if(pCherche!=null){
			
			this.produit=pCherche;
			this.indice=true;
			
			return"rechercheProduit";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche n'a rien donné"));
			this.indice=false;
			return "rechercheProduit";
		}		
	}
	
	
	public String supprProduit(){
		
		int verif = produitService.supprProduit(this.produit, this.categorie);
		
		if(verif!=0){
			
			List<Produit> listeProduit=produitService.getAllProduit();
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitListe", listeProduit);
			
			return "accueil";
			
		}else{
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppresion a échoué"));
			
			return "supprProduit";
		}			
	}
	
	
	public String modifProduit(){
		
		int verif = produitService.modifProduit(this.produit, this.categorie);
		
		if(verif!=0){
			
			List<Produit> listeProduit=produitService.getAllProduit();
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitListe", listeProduit);
		
			return"accueil";
			
		}else{
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification n'a pas été effectué"));
			
			return "modifProduit";
		}			
	}
	
	
	public String getAllProduit(){
		
		List<Produit> produitListe= produitService.getAllProduit();
		
		if(produitListe!=null){			
			
			
			this.listeP=produitListe;
			
			return "listeProduit";
			
			
		}else{
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La liste ne peut pas s'afficher"));
			
			return "listeProduit";
		}		
	}	
	
}
