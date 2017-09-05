package rmvanti.medicalrecord.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import rmvanti.medicalrecord.dao.AbstractAutoNumberEntity;

/**
 * @author rmvanti
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Patient.findAll", query="select pat from Patient pat")
})
public class Patient extends AbstractAutoNumberEntity {
    
    private String name;
    private String phone;
    private String cpf;
    
    public Patient(){}

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
            
}//end class
