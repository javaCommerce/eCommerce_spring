package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao {

	/** D�claration de l'attribut SessionFactoru */
	@Autowired
	private SessionFactory sf;

	/** Setter pour l'injection d�pendance */
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Commande addCommande(Commande co) {

		/** R�cup�ration de la session */
		Session s = sf.getCurrentSession();

		/** Ajout de la commande � la base de donn�es */
		s.save(co);

		return co;
	}

	@Override
	public int deleteCommande(Commande co) {

		try {
			// r�cup�rer la session
			Session s = sf.getCurrentSession();

			// delete l'�tudiant
			s.delete(co);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return 0;
	}

	@Override
	public int updateCommande(Commande co) {

		try {

			// r�cup�rer la session
			Session s = sf.getCurrentSession();

			// r�cup�rer l'�tudiant � modifier
			Commande commandeOut = (Commande) s.get(Commande.class, co.getIdCommande());

			// modifier eOut
			commandeOut.setDateCommande(co.getDateCommande());

			// update l'�tudiant
			s.saveOrUpdate(commandeOut);

			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return 0;
	}

	@Override
	public Commande getCommandeById(Commande co) {

		/** R�cup�ration de la session */
		Session s = sf.getCurrentSession();

		/** Requ�te HQL */
		String req = "FROM Commande co WHERE co.idCommande=:pId";

		/** R�cup�ration du query */
		Query query = s.createQuery(req);

		/** Passage des param�tres */
		query.setParameter("pId", co.getIdCommande());

		/** Envoie de la requ�te et r�cup�ration du r�sultat */
		return (Commande) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commande> getAllCommande(Client c) {

		/** R�cup�rer la session */
		Session s = sf.getCurrentSession();

		/** Requ�te HQL */
		String req = "FROM Commande co WHERE co.client.idClient=:pId";

		/** R�cup�ration du query */
		Query query = s.createQuery(req);

		/** Passage des param�tres */
		query.setParameter("pId", c.getIdClient());

		/** Envoie de la requ�te et r�cup�ration du r�sultat */
		return query.list();
	}

}
