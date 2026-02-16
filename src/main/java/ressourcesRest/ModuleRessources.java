package ressourcesRest;


import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("module")
public class ModuleRessources {
    private static ModuleBusiness mb = new ModuleBusiness();
    private static UniteEnseignementBusiness ueb = new UniteEnseignementBusiness();




    @POST
    @Consumes(MediaType.APPLICATION_XML)
    //deuxeme méthode :@Consumes("/application/xml")
    //@Produces(MediaType.TEXT_PLAIN):on l'utilise qon on a un affichage
    public Response ajouterModule(Module module){
        //return ueb.addUniteEnseignement(UE);
        if(mb.addModule(module)){
            return Response.status(Response.Status.CREATED).entity("success").build();
        return Response.status(Response.Status.NOT_FOUND).entity("failed").build();

    }

@GET
@Produces(MediaType.APPLICATION_XML)
public Response get

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEBySemestre(@QueryParam("semestre") String semestre,@QueryParam("codeUE") String codeUE) {
        if(semestre != null) {
            return Response.status(Response.Status.OK).entity(ueb.getUEBySemestre(Integer.parseInt(semestre))).build();

        }
        if(codeUE!=null){
            return Response.status(Response.Status.OK).entity(ueb.getUEByCode(Integer.parseInt(codeUE))).build();
        }
        return Response.status(Response.Status.FOUND).entity(ueb.getListeUE()).build();
    }


    @GET
    @Path("{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Module getModuleByMatricule(@PathParam("matricule") String matricule) {
        return mb.getModuleByMatricule(matricule);
    }

    @DELETE
    @Path("{matricule}")
    public boolean supprimerModule(@PathParam("matricule") String matricule) {
        return mb.deleteModule(matricule);
    }


    @PUT
    @Path("{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean modifierModule(@PathParam("matricule") String matricule,
                                  Module module) {
        return mb.updateModule(matricule, module);
    }


    @GET
    @Path("/UE")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> getModulesByUE(@QueryParam("codeUE") int codeUE) {
        UniteEnseignement ue = ueb.getUEByCode(codeUE);
        if (ue != null) {
            return mb.getModulesByUECode(codeUE);
        }
        return null;
    }

}
