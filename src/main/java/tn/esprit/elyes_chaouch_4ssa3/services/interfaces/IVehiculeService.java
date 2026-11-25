package tn.esprit.elyes_chaouch_4ssa3.services.interfaces;

import tn.esprit.elyes_chaouch_4ssa3.entities.Vehicule;
import java.util.List;

public interface IVehiculeService {
    Vehicule ajouterVehicule(Vehicule a);
    Vehicule updateVehicule(Vehicule a);
    void deleteVehicule(Long id);
    Vehicule getVehicule(Long id);
    List<Vehicule> getAllVehicules();
    Vehicule affecterVehicule(Long idVehicule, Long idAgence);
    Vehicule affecterEquipement(Long idVehicule, Long idEquipement);
    Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement);
}
