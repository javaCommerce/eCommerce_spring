package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.entities.Client;

@Service("clService")
@Transactional
public class ClientServiceImpl implements IClientService {

	/** Transformation de l'association UML en Java */
	@Autowired
	private IClientDao clientDao;

	/** Le setter obligatoire pour l'injection de dépendance */
	public void setClientDao(IClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public Client addClient(Client c) {
		return clientDao.addClient(c);
	}

	@Override
	public int deleteClient(Client c) {

		/** Récupération du client que l'on souhaite supprimer */
		Client clientSuppr = clientDao.getClientById(c);

		if (clientSuppr != null) {
			clientDao.deleteClient(clientSuppr);
		}

		return 0;
	}

	@Override
	public int updateClient(Client c) {
		return clientDao.updateClient(c);
	}

	@Override
	public Client getClientById(Client c) {
		return clientDao.getClientById(c);
	}

	@Override
	public List<Client> getAllClient() {
		return clientDao.getAllClient();
	}

	@Override
	public Client isExist(Client c) {
		return clientDao.isExist(c);
	}

}
