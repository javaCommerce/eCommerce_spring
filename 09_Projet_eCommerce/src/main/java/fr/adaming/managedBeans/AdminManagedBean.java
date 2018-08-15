package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.entities.Admin;
import fr.adaming.service.IAdminService;

@ManagedBean(name = "aMB")
@RequestScoped
public class AdminManagedBean implements Serializable {

	/** D�claration des attribus */

	private Admin admin;

	/** Transformation de l'association uml en java */
	@ManagedProperty(value = "#{aService}")
	private IAdminService adminService;

	/** Setter pour l'injection d�pendance */

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	/** Constructeur vide */

	public AdminManagedBean() {
		super();
		this.admin = new Admin();
	}

	/** D�claration des getter et setter */

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	/** D�claration de la m�thode Connection */

	public String connection() {

		/** Appel de la m�thode isExist */
		Admin aOut = adminService.isExist(admin);

		if (aOut != null) {

			/** Mettre l'admin comme attribut de la session */
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aSession", aOut);

			return "accueil";
		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vous n'�tes pas r�f�renc�"));
			return "login";
		}

	}

}
