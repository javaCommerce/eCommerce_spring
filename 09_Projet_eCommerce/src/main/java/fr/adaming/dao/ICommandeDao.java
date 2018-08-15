package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;

public interface ICommandeDao {

	public Commande addCommande(Commande co);

	public int deleteCommande(Commande co);

	public int updateCommande(Commande co);

	public Commande getCommandeById(Commande co);

	public List<Commande> getAllCommande(Client c);

}
