package com.br.levelup.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY =
            createEntityManagerFactory("tests");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

}
