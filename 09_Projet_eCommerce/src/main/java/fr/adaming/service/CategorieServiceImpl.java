package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.entities.Categorie;

@Service("catService")
@Transactional
public class CategorieServiceImpl implements ICategorieService{

	
	/**Transformation de l'association uml en java*/
	@Autowired
	private ICategorieDao categorieDao;
	
	
	/**Déclaration du setter pour l'injection dépendance*/
	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}
	
	/**Déclaration des méthodes*/
	
	
	@Override
	public Categorie addCategorie(Categorie cat) {
		
		return categorieDao.addCategorie(cat);
	}

	@Override
	public int supprCategorie(Categorie cat) {
	
		return categorieDao.supprCategorie(cat);
	}

	@Override
	public int modifCategorie(Categorie cat) {
		
		return categorieDao.modifCategorie(cat);
	}

	@Override
	public Categorie getCategorieById(Categorie cat) {
		
		return categorieDao.getCategorieById(cat);
	}

	@Override
	public List<Categorie> getAllCategorie() {
		
		return categorieDao.getAllCategorie();
	}

}
