package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;

@Service("coService")
@Transactional
public class CommandeServiceImpl implements ICommandeService {

	/** Transformation de l'association UML en Java */
	@Autowired
	private ICommandeDao commandeDao;

	/** Setter pour l'injection dépendance */
	public void setCommandeDao(ICommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	@Override
	public Commande addCommande(Commande co, Client c) {
		/** Lier les objets java */
		co.setClient(c);
		return commandeDao.addCommande(co);
	}

	@Override
	public int deleteCommande(Commande co, Client c) {
		// chercher l'étudiant avec son ID (pour vérifier si l'étudiant existe
		// et appartient au formateur)
		Commande coDel = this.getCommandeById(co, c);

		if (coDel != null) {
			return commandeDao.deleteCommande(coDel);
		}
		return 0;
	}

	@Override
	public int updateCommande(Commande co, Client c) {
		/** Lier les objets java */
		co.setClient(c);
		return commandeDao.updateCommande(co);
	}

	@Override
	public Commande getCommandeById(Commande co, Client c) {
		return commandeDao.getCommandeById(co);
	}

	@Override
	public List<Commande> getAllCommande(Client c) {
		return commandeDao.getAllCommande(c);
	}

}
