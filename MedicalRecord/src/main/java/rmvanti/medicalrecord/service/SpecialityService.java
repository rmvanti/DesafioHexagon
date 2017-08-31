package rmvanti.medicalrecord.service;

import com.google.gson.Gson;
import javax.ws.rs.Path;
import rmvanti.medicalrecord.dao.IDao;
import rmvanti.medicalrecord.model.Speciality;

/** 
 * @author rmvanti
 */
@Path("speciality")
public class SpecialityService {
    
    private Gson gson;
    private IDao<Speciality,Integer> dao;
    
    public SpecialityService(){}
        
    
    
}//end class
