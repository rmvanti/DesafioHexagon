package rmvanti.medicalrecord.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author rmvanti
 */
@MappedSuperclass
public abstract class AbstractAutoNumberEntity implements Serializable, IIdentifiable<Integer>, ITimestantable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected final Date createdIn;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdate;
    
    public AbstractAutoNumberEntity(){
        this.createdIn = new Date();
    }

    @Override
    public Date getCreatedIn(){
        return this.createdIn;
    }

    @Override
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id){
        this.id = id;
    }            
                
}//fim class
