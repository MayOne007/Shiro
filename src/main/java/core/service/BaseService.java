package core.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E, ID extends Serializable> {	
	
	public List<E> listALL();
	
}
