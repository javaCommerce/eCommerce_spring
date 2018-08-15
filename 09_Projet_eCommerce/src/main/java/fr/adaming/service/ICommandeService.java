package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;

public interface ICommandeService {

	public Commande addCommande(Commande co, Client c);

	public int deleteCommande(Commande co, Client c);

	public int updateCommande(Commande co, Client c);

	public Commande getCommandeById(Commande co, Client c);

	public List<Commande> getAllCommande(Client c);

}
