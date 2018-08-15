package fr.adaming.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Produit;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateClient(Client c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Client getClientByNom(Client c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAllClient() {
		// TODO Auto-generated method stub
		return null;
	}

	// @SuppressWarnings("unchecked")
	// public List<Client> getAllClient() {
	//
	// /**
	// * Création de la requête JPQL pour récupérer les clients
	// */
	// String req = "SELECT cl FROM Client cl";
	//
	// /**
	// * Création du query
	// */
	// Query query = em.createQuery(req);
	//
	// /**
	// * Retourne la liste trouvée
	// */
	//
	// return query.getResultList();
	// }
	//
	// public int deleteClient(Client c) {
	// try {
	// /**
	// * remove le client
	// */
	// em.remove(c);
	// return 1;
	// } catch (Exception arg) {
	// arg.printStackTrace();
	//
	// }
	// return 0;
	//
	// }
	//
	// public Client getClientByNom(Client c) {
	//
	// /**
	// * Création de la requête JPQL
	// */
	// String req = "SELECT cl FROM Client cl WHERE cl.nomClient=:pNom";
	//
	// /**
	// * Création du query
	// */
	// Query query = em.createQuery(req);
	//
	// /**
	// * Passage des paramètres
	// */
	// query.setParameter("pNom", c.getNomClient());
	//
	// return (Client) query.getSingleResult();
	// }
	//
	// public int updateClient(Client c) {
	//
	// try {
	// /**
	// * récupérer le client à modifier
	// */
	// em.find(Client.class, c.getIdClient());
	// /**
	// * merge le client
	// */
	// em.merge(c);
	// return 1;
	// } catch (Exception arg) {
	// arg.printStackTrace();
	//
	// }
	// return 0;
	// }
	//
	// @Override
	// public List<Categorie> getAllCategories() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public List<Produit> getAllProduits(Categorie cat) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
