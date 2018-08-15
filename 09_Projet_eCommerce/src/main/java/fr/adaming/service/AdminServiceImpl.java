package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.entities.Admin;

@Service("aService")
@Transactional
public class AdminServiceImpl implements IAdminService {

	/** transformation de l'association uml en java */
	@Autowired
	private IAdminDao adminDao;

	/** Setter pour injection dépendance */

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/** Méthode isexist */

	@Override
	public Admin isExist(Admin a) {

		return adminDao.isExist(a);
	}

}
