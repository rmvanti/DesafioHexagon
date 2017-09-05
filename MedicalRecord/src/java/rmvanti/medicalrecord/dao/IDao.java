package rmvanti.medicalrecord.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author rmvanti
 * @param <E> Entity type
 * @param <K> Primary key type
 */
public interface IDao <E,K> extends Serializable{
    
    public void insert(E entity);
    public void update(E entity);
    public void delete(E entity);
    public long count();
        
    public List<E> findAll();
    public E findById(K primaryKey);
    
    public List<E> executeNamedQueryWithMultipleResult(String namedQueryName, Map params);
    public Object executeNamedQueryWithSingleResult(String namedQueryName, Map params);
        
    public List executeNamedNativeQueryWithMultipleResult(String namedNativeQueryName, List params);
    public Object executeNamedNativeQueryWithSingleResult(String namedNativeQueryName, Map params);

    public List<E> executeNativeQueryWithMultipleResult(String nativeQuery, Map params);
    public Object executeNativeQueryWithSingleResult(String nativeQuery, Map params);
        
}//fim interface
