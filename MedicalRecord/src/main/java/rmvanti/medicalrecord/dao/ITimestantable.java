package rmvanti.medicalrecord.dao;

import java.util.Date;

/**
 * @author rmvanti
 */
public interface ITimestantable {

    public Date getCreatedIn();
    
    public Date getLastUpdate();
    public void setLastUpdate(Date lastUpdate);
    
}//fim interface
