package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Client;

public interface IClientDao {

	public Client addClient(Client c);

	public int deleteClient(Client c);

	public int updateClient(Client c);

	public Client getClientByNom(Client c);

	public List<Client> getAllClient();

}