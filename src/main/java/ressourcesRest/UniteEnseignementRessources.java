package ressourcesRest;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("UE")
public class UniteEnseignementRessources {
    private static UniteEnseignementBusiness ueb=new UniteEnseignementBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    //deuxeme méthode :@Consumes("/application/xml")
    //@Produces(MediaType.TEXT_PLAIN):on l'utilise qon on a un affichage
    public Response ajouterUE(UniteEnseignement UE){
       //return ueb.addUniteEnseignement(UE);
        if(ueb.addUniteEnseignement(UE))
        return Response.status(Response.Status.CREATED).entity("success").build();
        return Response.status(Response.Status.NOT_FOUND).entity("failed").build();

    }


//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<UniteEnseignement> getAllUE() {
//        return ueb.getListeUE();
//    }

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



    @DELETE
    @Path("{code}")
    public Response supprimerUE(@PathParam("code") int code) {
        if(ueb.deleteUniteEnseignement(code))
            return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @Path("{code}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response modifierUE(@PathParam("code") int code, UniteEnseignement ue) {
        if(ueb.updateUniteEnseignement(code, ue))
            return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }



}
