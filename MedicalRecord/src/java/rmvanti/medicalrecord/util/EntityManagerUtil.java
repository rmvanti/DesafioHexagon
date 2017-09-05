package rmvanti.medicalrecord.util;

import java.io.Serializable;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author rmvanti
 */
@Singleton
public class EntityManagerUtil implements Serializable {
    
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    
    static {
        factory =  Persistence.createEntityManagerFactory("ApriscoPU");
        checkManagerIsOpen();
    }
    
    public static synchronized EntityManager getEntityManager(){
        checkFactoryIsOpen();
        checkManagerIsOpen();
        return manager;
    }
    
    private static void checkFactoryIsOpen(){
        if(factory == null || !factory.isOpen()){
            factory =  Persistence.createEntityManagerFactory("ApriscoPU");
        }
    }
    
    private static void checkManagerIsOpen(){
        if(manager == null || !manager.isOpen()){
            if(manager != null){
                manager.clear();
            }            
            manager = factory.createEntityManager();
        }
    }        
    
    public static void close(){
        manager.close();
        factory.close();        
    }
    
}//end class
