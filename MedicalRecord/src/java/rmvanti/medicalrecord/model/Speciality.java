package rmvanti.medicalrecord.model;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import rmvanti.medicalrecord.dao.AbstractAutoNumberEntity;

/**
 * @author rmvanti
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Speciality.findAll", query="select spec from Speciality spec")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Speciality.findDoctorBySpeciality", query = "select * from doctor where id in (select doctor_id from doctor_speciality where especialities_id = ?)")
})
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
