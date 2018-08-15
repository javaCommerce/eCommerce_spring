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
import fr.adaming.entities.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {
	
	/**Declaration des attribus*/	
	private Categorie categorie;
	private Admin admin;
	private boolean indice;
	
	/**transformation de l'association uml en java*/
	@ManagedProperty(value = "#{catService}")
	private ICategorieService categorieService;

	/**setter pour l'injection dépendance*/
	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}
		
	/**Méthode postconstruc*/
	@PostConstruct
	public void init(){
		admin=(Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aSession");
		this.indice=false;
	}
	
	/**Déclaration du constructeur vide*/
	public CategorieManagedBean() {
		super();
		this.categorie= new Categorie();
		this.admin = new Admin();
	}
	
	/**Déclaration des getter et setter*/
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
	
	
	/**Déclaration des méthodes*/
	
	public String ajouterCategorie(){
		
		Categorie catIn = categorieService.addCategorie(this.categorie);
		
		if(catIn!=null){
			
			List<Categorie> listeCategorie = categorieService.getAllCategorie();
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieListe", listeCategorie);		
					
			return "accueil";
			
		}else{
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout de la catégorie à échoué"));
		return "ajoutCategorie";
		}		
	}
	
	
	public String supprCategorie(){
		
		int verif = categorieService.supprCategorie(this.categorie);
		
		if(verif!=0){
			
			List<Categorie> listeCategorie = categorieService.getAllCategorie();
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieListe", listeCategorie);		
			return "accueil";			
			
		}else{
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression de la catégorie à échoué"));
			return "supprCategorie";			
		}	
	}
	
	
	public String modifCategorie(){
		
		int verif = categorieService.modifCategorie(this.categorie);
		
		if(verif!=0){
			
			List<Categorie> listeCategorie = categorieService.getAllCategorie();
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieListe", listeCategorie);		
			return "accueil";
		}else{
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification de la catégorie à échoué"));
			return "modifCategorie";			
		}	
	}
	
	
	public String getCategorieById(){
		
		Categorie catCherch = categorieService.getCategorieById(this.categorie);
		
		if(catCherch!=null){
			
			this.categorie=catCherch;	
			this.indice=true;
			
			return "rechercheCategorie";
		}else{
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche de la catégorie à échoué"));
		this.indice=false;
		return "rechercheCategorie";
		}				
	}
	
	
	public String getAllCategorie(){
		
		Categorie categorieListe = (Categorie) categorieService.getAllCategorie();
		
		if(categorieListe!=null){
			
			this.categorie=categorieListe;
			
			return"listeCategorie";		
			
		}else{
		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'affichage des catégories à échoué"));
			
			return "listeCategorie";
	}
}
	
}
