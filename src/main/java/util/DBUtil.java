package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * DBUtilクラス
 * DAO（Data Access Object）
 * 他のEclipseプロジェクトでも流用可能
 */
public class DBUtil {
    
    private static final String PERSISTENCE_UNIT_NAME = "tasklist";
    private static EntityManagerFactory emf;
    
    public static EntityManager createEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
    
    private static EntityManagerFactory getEntityManagerFactory() {
        
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        
        return emf;
    }
}
