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

	/** D�claration de l'attribut SessionFactory */
	@Autowired
	private SessionFactory sf;

	/** Le setter obligatoire pour l'injection de d�pendance */
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	public Client addClient(Client c) {

		/** R�cup�ration de la session */
		Session s = sf.getCurrentSession();

		/** Ajout du client � la base de donn�es */
		s.save(c);

		return c;
	}

	@Override
	public int deleteClient(Client c) {

		try {
			/** R�cup�ration de la session */
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

			/** r�cup�rer la session */
			Session s = sf.getCurrentSession();

			/** R�cup�rer le client que l'on souhaite modifier */
			Client cModif = (Client) s.get(Client.class, c.getIdClient());

			/** Modifier les valeurs des attributs du client */
			cModif.setNomClient(c.getNomClient());
			cModif.setEmail(c.getEmail());
			cModif.setAdresse(c.getAdresse());
			cModif.setTel(c.getTel());

			/** On modifie le client via la m�thode update de session */
			s.saveOrUpdate(cModif);

			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return 0;

	}

	@Override
	public Client getClientById(Client c) {

		/** R�cup�ration de la session */
		Session s = sf.getCurrentSession();

		/** Requ�te HQL */
		String req = "FROM Client cl WHERE cl.id=:pId";

		/** R�cup�ration du query */
		Query query = s.createQuery(req);

		/** Passage des param�tres */
		query.setParameter("pId", c.getIdClient());

		/** Envoie de la requ�te et r�cup�ration du r�sultat */
		return (Client) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllClient() {

		/** R�cup�ration de la session */
		Session s = sf.getCurrentSession();

		/** Requ�te HQL */
		String req = "FROM Client cl";

		/** R�cup�ration du query */
		Query query = s.createQuery(req);

		/** Envoie de la requ�te et r�cup�ration des r�sultats */
		return query.list();
	}

}
