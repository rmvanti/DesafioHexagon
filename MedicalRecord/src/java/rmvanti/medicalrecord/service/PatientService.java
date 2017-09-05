package rmvanti.medicalrecord.service;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import rmvanti.medicalrecord.dao.Dao;
import rmvanti.medicalrecord.dao.IDao;
import rmvanti.medicalrecord.model.Patient;

/**
 * @author rmvanti
 */
@Path("/patient")
public class PatientService {
    
    private final IDao<Patient,Integer> dao;
    private final Gson gson;
    
    public PatientService(){
        this.dao = new Dao(Patient.class, Persistence.createEntityManagerFactory("MedicalRecordPU").createEntityManager());
        this.gson = new Gson();
    }
    
    @POST
    @Consumes
    public Response insert(String json){
        Patient pat = this.gson.fromJson(json, Patient.class);
        this.dao.insert(pat);
        return Response.ok().build();
    }
    
    @POST
    @Path("/{patientId}")
    @Consumes
    public Response update(@PathParam("patientId")int patientId, String json){
        //model
        Patient model = this.gson.fromJson(json, Patient.class);
        //entity
        Patient pat = this.dao.findById(patientId);
        pat.setCpf(model.getCpf());
        pat.setName(model.getName());
        pat.setPhone(model.getPhone());
        this.dao.update(pat);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{patientId}")
    public Response delete(@PathParam("patientId") int patientId){
        Patient pat = this.dao.findById(patientId);
        this.dao.delete(pat);
        return Response.ok().build();
    }
    
    @GET
    public Response getList(){
        List<Patient> list = this.dao.findAll();
        return Response.ok(this.gson.toJson(list)).build();
    }
    
    @GET
    @Path("/{patientId}")
    public Response getById(@PathParam("patientId") int patientId){
        Patient pat = this.dao.findById(patientId);
        return Response.ok(this.gson.toJson(pat)).build();
    }
        
}//end class
