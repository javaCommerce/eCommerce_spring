package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao{

	/**D�claration de l'attribut session factory*/
	@Autowired
	private SessionFactory sf;
	
	
	/**Setter pour l'injection d�pendance*/
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	
	@Override
	public Categorie addCategorie(Categorie cat) {
		
		/**recup�ration de la Session*/		
		Session s = sf.getCurrentSession();
		
		/**M�thode save pour ajouter*/
		s.save(cat);		
		
		/**Envoyer la requeter et r�cup�rer le r�sultat*/
		return cat;
	}


	@Override
	public int supprCategorie(Categorie cat) {

		/**recup�ration de la Session*/
		Session s=sf.getCurrentSession();
		
		
		/**Requete hql*/
		String req = "DELETE FROM Categorie cat WHERE cat.id=:pId";
		
		/**Recup�ration du query*/
		Query query = s.createQuery(req);
		
		/**passage des parametre*/		
		query.setParameter("pId", cat.getIdCategorie());		
		
		/**Envoyer la requeter et r�cup�rer le r�sultat*/
		return query.executeUpdate();
	}
		

	@Override
	public int modifCategorie(Categorie cat) {
		
		/**recup�ration de la Session*/
		Session s =sf.getCurrentSession();
		
		/**requete hql*/		
		String req ="UPDATE Categorie cat SET cat.description=:pDescription, cat.nomCategorie=:pNomCategorie, cat.photo=:pPhoto WHERE cat.id=:pId";
		
		/**Recup�ration du query*/
		Query query=s.createQuery(req);
		
		/**passage des parametres*/		
		query.setParameter("pDescription", cat.getDescription());
		query.setParameter("pNomCategorie", cat.getNomCategorie());
		query.setParameter("pPhoto", cat.getPhoto());
		query.setParameter("pId", cat.getIdCategorie());
		
		/**Envoyer la requeter et r�cup�rer le r�sultat*/
		return query.executeUpdate();
	}
	
	
	@Override
	public Categorie getCategorieById(Categorie cat) {
		
		/**recup�ration de la Session*/
		Session s=sf.getCurrentSession();
		
		/**requete hql*/		
		String req ="FROM Categorie cat WHERE cat.id=:pId";
		
		/**Recup�ration du query*/
		Query query =s.createQuery(req);
		
		/**Passage des parametre*/		
		query.setParameter("pId", cat.getIdCategorie());	
		
		cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		
		/**Envoyer la requeter et r�cup�rer le r�sultat*/
		return (Categorie) query.uniqueResult();
	}
	
	
	@Override
	public List<Categorie> getAllCategorie() {
		
		/**recup�ration de la Session*/		
		Session s=sf.getCurrentSession();
		
		/**Requete hql*/
		String req ="FROM Categorie cat";
		
		/**Recup�ration du query*/
		Query query=s.createQuery(req);
		
		List<Categorie> listeCategorie = query.list();
		
		for (Categorie cat : listeCategorie) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		
		/**Envoyer la requeter et r�cup�rer le r�sultat*/
		return listeCategorie;
	}	
}
