package fr.adaming.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.entities.Client;

@Service("clService")
@Transactional
public class ClientServiceImpl implements IClientService {

	/** Transformation de l'association UML en Java */
	IClientDao clientDao;

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

}
