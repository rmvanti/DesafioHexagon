package rmvanti.medicalrecord.service;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author rmvanti
 */
@ApplicationPath("rs")
public class ServiceConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set set = new HashSet();
        //set.add(SpecialityService.class);
        return set;        
    }
                    
}//end class
