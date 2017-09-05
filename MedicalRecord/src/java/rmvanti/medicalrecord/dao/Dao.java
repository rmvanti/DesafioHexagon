package rmvanti.medicalrecord.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author rmvanti
 * @param <E> entity class
 * @param <K> primary key class
 */
public class Dao<E,K> implements IDao<E,K> {

    private final EntityManager manager;
    private final Class clazz;
    private final List<Class> interfaces;
    private final boolean isSoftDelete;
    private final boolean isTimestantable;

    public Dao(Class clazz, EntityManager manager) {
        this.manager = manager;
        this.clazz = clazz;
        
        this.interfaces = new ArrayList();               
        this.interfaces.addAll(Arrays.asList(this.clazz.getInterfaces()));
        
        this.isSoftDelete = this.interfaces.contains(ISoftDelete.class);
        this.isTimestantable = this.interfaces.contains(ITimestantable.class);
    }

    @Override
    public void insert(E entity) {
        this.manager.getTransaction().begin();
        verifyTimestantableEntity(entity);
        this.manager.persist(entity);
        this.manager.getTransaction().commit();
    }

    @Override
    public void update(E entity) {
        this.manager.getTransaction().begin();
        verifyTimestantableEntity(entity);
        this.manager.merge(entity);
        this.manager.getTransaction().commit();
    }

    private void verifyTimestantableEntity(E entity) {
        if (this.isTimestantable) {
            ITimestantable timestantableEntity = (ITimestantable) entity;
            timestantableEntity.setLastUpdate(new Date());
        }
    }

    @Override
    public void delete(E entity) {
        this.manager.getTransaction().begin();
        if (this.isSoftDelete) {
            ISoftDelete softEntity = (ISoftDelete) entity;
            softEntity.setIsDeleted(true);
            verifyTimestantableEntity(entity);
            this.manager.merge(entity);
        } else {
            this.manager.remove(entity);
        }
        this.manager.getTransaction().commit();
    }

    @Override
    public long count() {
        CriteriaQuery cq = this.manager.getCriteriaBuilder().createQuery();
        Root<E> rt = cq.from(this.clazz);
        cq.select(this.manager.getCriteriaBuilder().count(rt));
        Query q = this.manager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<E> findAll() {
        String queryName = this.clazz.getSimpleName()+".findAll";
        return executeNamedQueryWithMultipleResult(queryName, null);
    }

    @Override
    public E findById(K primaryKey) {
        return (E) this.manager.find(clazz, primaryKey);
    }

    @Override
    public List<E> executeNamedQueryWithMultipleResult(String namedQueryName, Map params) {
        Query query = this.manager.createNamedQuery(namedQueryName);
        if (params != null) {
            Set set = params.keySet();
            Iterator it = set.iterator();
            String key;
            while (it.hasNext()) {
                key = (String) it.next();
                query.setParameter(key, params.get(key));
            }//fim while
        }//fim if
        return new ArrayList(query.getResultList());
    }

    @Override
    public Object executeNamedQueryWithSingleResult(String namedQueryName, Map params) {
        Query query = this.manager.createNamedQuery(namedQueryName);
        if (params != null) {
            Set set = params.keySet();
            Iterator it = set.iterator();
            String key;
            while (it.hasNext()) {
                key = (String) it.next();
                query.setParameter(key, params.get(key));
            }//fim while
        }//fim if
        return query.getSingleResult();
    }

    @Override
    public List executeNamedNativeQueryWithMultipleResult(String namedNativeQueryName, List params) {
        Query query = this.manager.createNamedQuery(namedNativeQueryName);        
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
    }

    @Override
    public Object executeNamedNativeQueryWithSingleResult(String namedNativeQueryName, Map params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> executeNativeQueryWithMultipleResult(String nativeQuery, Map params) {
        Query query = this.manager.createNativeQuery(nativeQuery);
        if (params != null) {
            Set set = params.keySet();
            Iterator it = set.iterator();
            String key;
            while (it.hasNext()) {
                key = (String) it.next();
                query.setParameter(key, params.get(key));
            }//fim while
        }//fim if
        return new ArrayList(query.getResultList());
    }

    @Override
    public Object executeNativeQueryWithSingleResult(String nativeQuery, Map params) {
        Query query = this.manager.createNativeQuery(nativeQuery);
        if (params != null) {
            Set set = params.keySet();
            Iterator it = set.iterator();
            String key;
            while (it.hasNext()) {
                key = (String) it.next();
                query.setParameter(key, params.get(key));
            }//fim while
        }//fim if
        return query.getSingleResult();
    }        

}
