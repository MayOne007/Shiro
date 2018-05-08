package core.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import core.dao.BaseDaoImpl;

@Transactional
public class BaseServiceImpl<E, ID extends Serializable> implements BaseService<E, ID>{
	
	public final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	BaseDaoImpl<E, ID> baseDao;

	@Override
	public List<E> listALL() {
		return baseDao.listALL();
	}
	
}
