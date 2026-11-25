package tn.esprit.elyes_chaouch_4ssa3.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.elyes_chaouch_4ssa3.entities.Vehicule;
import tn.esprit.elyes_chaouch_4ssa3.services.interfaces.IVehiculeService;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/vehicule")
public class VehiculeController {
    IVehiculeService vehiculeService;

    @PostMapping("/ajouter")
    public Vehicule ajouterVehicule(@RequestBody Vehicule a) {
        return vehiculeService.ajouterVehicule(a);
    }

    @PutMapping("/modifier")
    public Vehicule updateVehicule(@RequestBody Vehicule a) {
        return vehiculeService.updateVehicule(a);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
    }

    @GetMapping("/get/{id}")
    public Vehicule getVehicule(@PathVariable Long id) {
        return vehiculeService.getVehicule(id);
    }

    @GetMapping("/getAll")
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    @PutMapping("/affecter/{idVehicule}/{idAgence}")
    public Vehicule affecterVehicule(@PathVariable Long idVehicule, @PathVariable Long idAgence) {
        return vehiculeService.affecterVehicule(idVehicule, idAgence);
    }

    @PutMapping("/affecter-equipement/{idVehicule}/{idEquipement}")
    public Vehicule affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.affecterEquipement(idVehicule, idEquipement);
    }

    @PutMapping("/desaffecter-equipement/{idVehicule}/{idEquipement}")
    public Vehicule desaffecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.desaffecterEquipement(idVehicule, idEquipement);
    }
}
