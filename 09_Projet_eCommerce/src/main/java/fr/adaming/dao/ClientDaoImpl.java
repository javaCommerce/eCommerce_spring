package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Client;
import fr.adaming.service.ClientServiceImpl;

@Repository
public class ClientDaoImpl implements IClientDao {

	/** Déclaration de l'attribut SessionFactory */
	@Autowired
	private SessionFactory sf;

	/** Le setter obligatoire pour l'injection de dépendance */
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	public Client addClient(Client c) {

		/** Récupération de la session */
		Session s = sf.getCurrentSession();

		/** Ajout du client à la base de données */
		s.save(c);

		return c;
	}

	@Override
	public int deleteClient(Client c) {

		try {
			/** Récupération de la session */
			Session s = sf.getCurrentSession();

			/** Delete du client */
			s.delete(c);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return 0;

	}

	@Override
	public int updateClient(Client c) {

		try {

			/** récupérer la session */
			Session s = sf.getCurrentSession();

			/** Récupérer le client que l'on souhaite modifier */
			Client cModif = (Client) s.get(Client.class, c.getIdClient());

			/** Modifier les valeurs des attributs du client */
			cModif.setNomClient(c.getNomClient());
			cModif.setEmail(c.getEmail());
			cModif.setAdresse(c.getAdresse());
			cModif.setTel(c.getTel());

			/** On modifie le client via la méthode update de session */
			s.saveOrUpdate(cModif);

			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return 0;

	}

	@Override
	public Client getClientById(Client c) {

		/** Récupération de la session */
		Session s = sf.getCurrentSession();

		/** Requête HQL */
		String req = "FROM Client cl WHERE cl.id=:pId";

		/** Récupération du query */
		Query query = s.createQuery(req);

		/** Passage des paramètres */
		query.setParameter("pId", c.getIdClient());

		/** Envoie de la requête et récupération du résultat */
		return (Client) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllClient() {

		/** Récupération de la session */
		Session s = sf.getCurrentSession();

		/** Requête HQL */
		String req = "FROM Client cl";

		/** Récupération du query */
		Query query = s.createQuery(req);

		/** Envoie de la requête et récupération des résultats */
		return query.list();
	}

}
