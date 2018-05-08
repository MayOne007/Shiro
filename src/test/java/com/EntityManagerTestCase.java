package com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public abstract class EntityManagerTestCase {
    
	
	
	@Autowired
	EntityManagerFactory emf;
    
	EntityManager em;
	
    @Before
    public void openManager()  throws Exception {
    	em = emf.createEntityManager();
		EntityManagerHolder emHolder = new EntityManagerHolder(em);
		TransactionSynchronizationManager.bindResource(emf, emHolder);
    }
    
    @After
    public void closeManager()  throws Exception {
    	TransactionSynchronizationManager.unbindResource(emf);
    	EntityManagerFactoryUtils.closeEntityManager(em);
    }

}