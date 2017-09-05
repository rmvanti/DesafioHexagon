package rmvanti.medicalrecord.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import rmvanti.medicalrecord.dao.AbstractAutoNumberEntity;

/**
 * @author rmvanti
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Doctor.findAll", query="select d from Doctor d")
})
public class Doctor extends AbstractAutoNumberEntity {

    private String name;
    private String phone;
    private String cpf;
    private String crm;
    
    public Doctor(){}
    
    @ManyToMany
    private List<Speciality> specialities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
        
}//end class
