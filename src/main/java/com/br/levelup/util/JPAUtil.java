package com.br.levelup.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class JPAUtil {

    private static EntityManagerFactory FACTORY;

    public static EntityManager getEntityManager(String persistenceUnitName) {
        FACTORY = createEntityManagerFactory(persistenceUnitName);
        return FACTORY.createEntityManager();
    }

}
