package rmvanti.medicalrecord.dao;

/**
 * @author rmvanti
 * @param <K> Primary key type
 */
public interface IIdentifiable<K> {
    
    public K getId();
    public void setId(K id);
    
}//fim interface
