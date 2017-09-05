package rmvanti.medicalrecord.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rmvanti.medicalrecord.dao.AbstractAutoNumberEntity_;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-04T09:48:07")
@StaticMetamodel(Patient.class)
public class Patient_ extends AbstractAutoNumberEntity_ {

    public static volatile SingularAttribute<Patient, String> phone;
    public static volatile SingularAttribute<Patient, String> name;
    public static volatile SingularAttribute<Patient, String> cpf;

}