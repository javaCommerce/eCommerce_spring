package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@Service("pService")
@Transactional
public class ProduitServiceImpl implements IProduitService {

	/** Transformation de l'association uml en java */
	@Autowired
	private IProduitDao produitDao;

	/** Setter pour l'injection dépendance */
	public void setProduitDao(IProduitDao produitDao) {
		this.produitDao = produitDao;
	}

	/** Methodes */

	@Override
	public Produit addProduit(Produit p, Categorie cat) {
		p.setCat(cat);
		return produitDao.addProduit(p);
	}

	@Override
	public int supprProduit(Produit p, Categorie cat) {
		p.setCat(cat);
		return produitDao.supprProduit(p);
	}

	@Override
	public int modifPoduit(Produit p, Categorie cat) {
		p.setCat(cat);
		return produitDao.modifPoduit(p);
	}

	@Override
	public List<Produit> getAllProduit(Categorie cat) {
		return produitDao.getAllProduit(cat);
	}

	@Override
	public Produit getProduitById(Produit p, Categorie cat) {
		p.setCat(cat);
		return produitDao.getProduitById(p);
	}

}
