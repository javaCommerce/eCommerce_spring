package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Admin;

@Repository
public class AdminDaoImpl implements IAdminDao {

	/** Déclaration de session factory */

	@Autowired
	private SessionFactory sf;

	/** Déclaration du setter pour l'injection dépendance */

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	/** Déclaration de la méthode isExist */

	@Override
	public Admin isExist(Admin a) {

		/** requete hql */
		String req = "FROM Admin a WHERE a.mail=:pMail AND a.mdp=:pMdp";

		/** Recupération de la session */

		Session s = sf.getCurrentSession();

		/** Recuperation du query */

		Query query = s.createQuery(req);

		/** Passage des parametres */

		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());

		/** Envoyer la requete et récupérer le résultat */

		return (Admin) query.uniqueResult();
	}

}
