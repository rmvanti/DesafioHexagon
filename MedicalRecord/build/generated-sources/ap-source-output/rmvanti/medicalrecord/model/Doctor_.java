package rmvanti.medicalrecord.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rmvanti.medicalrecord.dao.AbstractAutoNumberEntity_;
import rmvanti.medicalrecord.model.Speciality;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-04T09:48:07")
@StaticMetamodel(Doctor.class)
public class Doctor_ extends AbstractAutoNumberEntity_ {

    public static volatile SingularAttribute<Doctor, String> phone;
    public static volatile SingularAttribute<Doctor, String> name;
    public static volatile SingularAttribute<Doctor, String> cpf;
    public static volatile ListAttribute<Doctor, Speciality> specialities;
    public static volatile SingularAttribute<Doctor, String> crm;

}