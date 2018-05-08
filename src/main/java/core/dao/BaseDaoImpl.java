package core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDaoImpl<E, ID extends Serializable> implements BaseDao<E, ID> {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@PersistenceContext
	private EntityManager em;

	Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.entityClass = (Class<E>) getSuperClassGenricType(getClass(), 0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Class<Object> getSuperClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}

		return (Class) params[index];
	}

	@SuppressWarnings("unchecked")
	public List<E> listALL() {		
		return em.createQuery("SELECT c FROM "+entityClass.getSimpleName()+" c ").getResultList();

	}

}
