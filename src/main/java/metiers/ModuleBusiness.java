package metiers;

import entities.Module;
import entities.UniteEnseignement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModuleBusiness {

    private static List<Module> modules = new ArrayList<>();
    private UniteEnseignementBusiness uniteEnseignementBusiness = new UniteEnseignementBusiness();

    public ModuleBusiness() {
        if (modules.isEmpty()) {
            modules.add(new Module("M101", "Algorithmique", 3, 30,
                    Module.TypeModule.PROFESSIONNEL,
                    uniteEnseignementBusiness.getUEByCode(1)));

            modules.add(new Module("M102", "Base de données", 2, 20,
                    Module.TypeModule.PROFESSIONNEL,
                    uniteEnseignementBusiness.getUEByCode(1)));

            modules.add(new Module("M201", "Communication", 1, 15,
                    Module.TypeModule.TRANSVERSAL,
                    uniteEnseignementBusiness.getUEByCode(2)));
        }
    }

    public boolean addModule(Module module) {
        UniteEnseignement ue = uniteEnseignementBusiness
                .getUEByCode(module.getUniteEnseignement().getCode());

        if (ue != null && !modules.contains(module)) {
            module.setUniteEnseignement(ue);
            return modules.add(module);
        }
        return false;
    }

    public Module getModuleByMatricule(String matricule) {
        return modules.stream()
                .filter(m -> m.getMatricule().equals(matricule))
                .findFirst()
                .orElse(null);
    }

    public boolean updateModule(String matricule, Module updatedModule) {
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getMatricule().equals(matricule)) {
                modules.set(i, updatedModule);
                return true;
            }
        }
        return false;
    }

    public boolean deleteModule(String matricule) {
        Iterator<Module> it = modules.iterator();
        while (it.hasNext()) {
            if (it.next().getMatricule().equals(matricule)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public List<Module> getAllModules() {
        return modules;
    }


    public List<Module> getModulesByUECode(int codeUE) {
        List<Module> result = new ArrayList<>();
        for (Module m : modules) {
            if (m.getUniteEnseignement() != null &&
                    m.getUniteEnseignement().getCode() == codeUE) {
                result.add(m);
            }
        }
        return result;
    }
}
