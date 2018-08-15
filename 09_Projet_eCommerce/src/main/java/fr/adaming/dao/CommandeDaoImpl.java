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

	/** Déclaration de l'attribut SessionFactoru */
	@Autowired
	private SessionFactory sf;

	/** Setter pour l'injection dépendance */
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Commande addCommande(Commande co) {

		/** Récupération de la session */
		Session s = sf.getCurrentSession();

		/** Ajout de la commande à la base de données */
		s.save(co);

		return co;
	}

	@Override
	public int deleteCommande(Commande co) {

		try {
			// récupérer la session
			Session s = sf.getCurrentSession();

			// delete l'étudiant
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

			// récupérer la session
			Session s = sf.getCurrentSession();

			// récupérer l'étudiant à modifier
			Commande commandeOut = (Commande) s.get(Commande.class, co.getIdCommande());

			// modifier eOut
			commandeOut.setDateCommande(co.getDateCommande());

			// update l'étudiant
			s.saveOrUpdate(commandeOut);

			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return 0;
	}

	@Override
	public Commande getCommandeById(Commande co) {

		/** Récupération de la session */
		Session s = sf.getCurrentSession();

		/** Requête HQL */
		String req = "FROM Commande co WHERE co.idCommande=:pId";

		/** Récupération du query */
		Query query = s.createQuery(req);

		/** Passage des paramètres */
		query.setParameter("pId", co.getIdCommande());

		/** Envoie de la requête et récupération du résultat */
		return (Commande) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commande> getAllCommande(Client c) {

		/** Récupérer la session */
		Session s = sf.getCurrentSession();

		/** Requête HQL */
		String req = "FROM Commande co WHERE co.client.idClient=:pId";

		/** Récupération du query */
		Query query = s.createQuery(req);

		/** Passage des paramètres */
		query.setParameter("pId", c.getIdClient());

		/** Envoie de la requête et récupération du résultat */
		return query.list();
	}

}
