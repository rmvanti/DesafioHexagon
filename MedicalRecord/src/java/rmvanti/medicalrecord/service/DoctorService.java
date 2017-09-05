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
import rmvanti.medicalrecord.model.Doctor;
import rmvanti.medicalrecord.viewmodel.DoctorViewModel;

/**
 * @author rmvanti
 */
@Path("/doctor")
public class DoctorService {
    
    private final IDao<Doctor,Integer> dao;
    private final Gson gson;
    
    public DoctorService(){
        this.dao = new Dao(Doctor.class, Persistence.createEntityManagerFactory("MedicalRecordPU").createEntityManager());
        this.gson = new Gson();
    }
    
    @POST
    @Consumes
    public Response insert(String json){
        DoctorViewModel model = this.gson.fromJson(json, DoctorViewModel.class);
        Doctor doc = new Doctor();
        doc.setName(model.getName());
        doc.setPhone(model.getPhone());
        doc.setCpf(model.getCpf());
        doc.setCrm(model.getCrm());
        //TODO: add specialities
        this.dao.insert(doc);
        return Response.ok().build();
    }
    
    @POST
    @Path("/{doctorId}")
    @Consumes
    public Response update(@PathParam("doctorId") int doctorId, String json){
        DoctorViewModel model = this.gson.fromJson(json, DoctorViewModel.class);
        Doctor doc = this.dao.findById(doctorId);
        doc.setName(model.getName());
        doc.setPhone(model.getPhone());
        doc.setCpf(model.getCpf());
        doc.setCrm(model.getCrm());
        //TODO: add/remove specialities
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{doctorId}")
    public Response delete(@PathParam("doctorId") int doctorId){
        Doctor doc = this.dao.findById(doctorId);
        this.dao.delete(doc);
        return Response.ok().build();
    }

    @GET
    @Path("/{doctorId}")
    public Response get(@PathParam("doctorId") int doctorId){
        Doctor doc = this.dao.findById(doctorId);
        return Response.ok(this.gson.toJson(doc)).build();
    }
    
    @GET
    public Response getList(){
        List<Doctor> list = this.dao.findAll();
        return Response.ok(this.gson.toJson(list)).build();
    }
    
}//end class
