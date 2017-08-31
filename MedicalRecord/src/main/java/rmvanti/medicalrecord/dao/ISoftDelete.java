package rmvanti.medicalrecord.dao;

/**
 * @author rmvanti
 */
public interface ISoftDelete {
 
    public boolean isDeleted();
    public void setIsDeleted(boolean isDeleted);
    
}
