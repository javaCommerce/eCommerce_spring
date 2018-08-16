package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {

	/** declaration de l'attribut sessionFactory */
	@Autowired
	private SessionFactory sf;

	/** D�claration du setter */
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	/** D�claration des m�thodes */

	@Override
	public Produit addProduit(Produit p) {
		
		/**recup�ration de la session*/
		Session s = sf.getCurrentSession();
		
		/**M�thode save*/
		s.save(p);
		
		
		return p;
	}

	@Override
	public int supprProduit(Produit p) {

		/**recup�ration de la session*/
		Session s = sf.getCurrentSession();

		/**D�claration de la requete hql*/
		String req = "DELETE FROM Produit p WHERE p.id=:pIdP";

		/**recup�ration du query*/
		Query query = s.createQuery(req);

		/** Passage des parametres */		
		query.setParameter("pIdP", p.getIdProduit());

		return query.executeUpdate();
	}

	@Override
	public int modifProduit(Produit p) {

		/**recup�ration de la session*/
		Session s = sf.getCurrentSession();

		/**D�claration de la requete hql*/
		String req = "UPDATE Produit p SET p.description=:pDescription, p.designation=:pDesignation, p.photo=:pPhoto, p.prix=:pPrix, p.quantite=:pQuantite WHERE p.id=:pIdP";

		/**recup�ration du query*/
		Query query = s.createQuery(req);
		
		/**Passage des parametres*/		
		query.setParameter("pDescription", p.getDescription());
		query.setParameter("pDesignation", p.getDesignation());
		query.setParameter("pPhoto", p.getPhoto());
		query.setParameter("pPrix", p.getPrix());
		query.setParameter("pQuantite", p.getQuantite());
		query.setParameter("pIdP", p.getIdProduit());
		
		return query.executeUpdate();
	}

	@Override
	public List<Produit> getAllProduit(Categorie cat) {
		
		/**recup�ration de la session*/
		Session s = sf.getCurrentSession();
		
		/**D�claration de la requete hql*/
		String req="FROM Produit p WHERE p.categorie.id=:pIdCat";
		
		/**recup�ration du query*/
		Query query = s.createQuery(req);
		
		/**Passage des parametres*/
		query.setParameter("pIdCat", cat.getIdCategorie());		
		
		List<Produit> listeProduit=query.list();
		
		for (Produit p : listeProduit) {
			p.setImage("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
		}
		
		return listeProduit;
	}

	
	@Override
	public Produit getProduitById(Produit p) {
		
		/**recup�ration de la session*/
		Session s = sf.getCurrentSession();
		
		/**D�claration de la requete hql*/
		String req = "FROM Produit p WHERE p.id=:pIdP";
		
		/**recup�ration du query*/
		Query query = s.createQuery(req);
		
		/**passage des parametres*/				
		query.setParameter("pIdP", p.getIdProduit());
		
		p.setImage("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
				
		return (Produit) query.uniqueResult();
	}
}
