package core.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<E, ID extends Serializable>{	

	public List<E> listALL();
	
}
