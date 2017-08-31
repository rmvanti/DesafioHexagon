package rmvanti.medicalrecord.model;

import javax.persistence.Entity;
import rmvanti.medicalrecord.dao.AbstractAutoNumberEntity;

/**
 * @author rmvanti
 */
@Entity
public class Speciality extends AbstractAutoNumberEntity {
    
    private String name;

    public Speciality(){}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
            
}//end class
