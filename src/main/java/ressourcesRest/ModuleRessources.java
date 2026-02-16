package ressourcesRest;


import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("module")
public class ModuleRessources {
    private static ModuleBusiness mb = new ModuleBusiness();
    private static UniteEnseignementBusiness ueb = new UniteEnseignementBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean ajouterModule(Module module) {
        return mb.addModule(module);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> getAllModules() {
        return mb.getAllModules();
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