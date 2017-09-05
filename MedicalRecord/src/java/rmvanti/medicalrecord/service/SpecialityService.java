package rmvanti.medicalrecord.service;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import rmvanti.medicalrecord.dao.Dao;
import rmvanti.medicalrecord.dao.IDao;
import rmvanti.medicalrecord.model.Doctor;
import rmvanti.medicalrecord.model.Speciality;

/**
 * @author rmvanti
 */
@Path("/speciality")
public class SpecialityService {

    private final Gson gson;
    private final IDao<Speciality, Integer> dao;

    public SpecialityService() {
        this.gson = new Gson();
        this.dao = new Dao(Speciality.class, Persistence.createEntityManagerFactory("MedicalRecordPU").createEntityManager());
    }

    @POST
    @Consumes
    public Response insert(String json) {
        System.out.println("Passei no insert: " + json);
        Speciality spec = this.gson.fromJson(json, Speciality.class);
        System.out.println(spec.getName());
        this.dao.insert(spec);
        return Response.ok().build();
    }

    @GET
    @Produces
    public Response getList() {
        List<Speciality> list = this.dao.findAll();
        return Response.ok(this.gson.toJson(list)).build();
    }

    @GET
    @Path("/doctors/{specialityId}")
    @Produces
    public Response getDoctorsBySpeciality(@PathParam("specialityId") int specialityId) {
        System.out.println("passei em getDoctorsBySpeciality: " + specialityId);
        List params = new ArrayList();
        params.add(specialityId);
        List<Doctor> doctors = (List<Doctor>) this.dao.executeNamedNativeQueryWithMultipleResult("Speciality.findDoctorBySpeciality", params);
        return Response.ok(this.gson.toJson(doctors)).build();
    }

    @GET
    @Path("/{specialityId}")
    @Produces
    public Response get(@PathParam("specialityId") int specialityId) {
        System.out.println("passei em get: " + specialityId);
        Speciality spec = this.dao.findById(specialityId);
        return Response.ok(this.gson.toJson(spec)).build();
    }

    @POST
    @Path("/{specialityId}")
    public Response update(@PathParam("specialityId") int specialityId, String json) {
        //Model
        Speciality model = this.gson.fromJson(json, Speciality.class);
        //Entity
        Speciality spec = this.dao.findById(specialityId);
        spec.setName(model.getName());
        this.dao.update(spec);
        return Response.ok().header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Allow-Credentials", "false")
                .header("Origin", "localhost/MeetingRoomReserve")
                .build();
    }

    @DELETE
    @Path("/{specialityId}")
    public Response delete(@PathParam("specialityId") int specialityId) {
        System.out.println("passei em delete: " + specialityId);
        Speciality spec = this.dao.findById(specialityId);
        this.dao.delete(spec);
        return Response.ok().build();
    }

    /*
    @OPTIONS
    public Response opts() {
        return Response.ok().header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Allow-Credentials", "false")
                .header("Origin", "localhost/MedicalRecord").build();
    }
    */
    
}//end class
