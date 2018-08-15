package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Admin;

@Repository
public class AdminDaoImpl implements IAdminDao {

	/** D�claration de session factory */

	@Autowired
	private SessionFactory sf;

	/** D�claration du setter pour l'injection d�pendance */

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	/** D�claration de la m�thode isExist */

	@Override
	public Admin isExist(Admin a) {

		/** requete hql */
		String req = "FROM Admin a WHERE a.mail=:pMail AND a.mdp=:pMdp";

		/** Recup�ration de la session */

		Session s = sf.getCurrentSession();

		/** Recuperation du query */

		Query query = s.createQuery(req);

		/** Passage des parametres */

		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());

		/** Envoyer la requete et r�cup�rer le r�sultat */

		return (Admin) query.uniqueResult();
	}

}
