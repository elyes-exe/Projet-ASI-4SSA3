package tn.esprit.elyes_chaouch_4ssa3.services.impls;

import lombok.AllArgsConstructor;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.elyes_chaouch_4ssa3.entities.Agence;
import tn.esprit.elyes_chaouch_4ssa3.entities.Equipement;
import tn.esprit.elyes_chaouch_4ssa3.reposetiries.VehiculeReposotory;
import tn.esprit.elyes_chaouch_4ssa3.entities.Vehicule;
import tn.esprit.elyes_chaouch_4ssa3.reposetiries.AgenceReposotirie;
import tn.esprit.elyes_chaouch_4ssa3.reposetiries.EquipementReposotory;
import tn.esprit.elyes_chaouch_4ssa3.services.interfaces.IVehiculeService;

import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@Service
public class VehiculeServiceImpl implements IVehiculeService {
    VehiculeReposotory vehiculeRepository;
    AgenceReposotirie agenceRepository;
    EquipementReposotory equipementRepository;

    @Override
    public Vehicule ajouterVehicule(Vehicule a) {
        return vehiculeRepository.save(a);
    }

    @Override
    public Vehicule updateVehicule(Vehicule a) {
        return vehiculeRepository.save(a);
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public Vehicule getVehicule(Long id) {
        return vehiculeRepository.findById(id).get();
    }

    @Override
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable: " + idVehicule));
        Agence nouvelleAgence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable: " + idAgence));

        Agence ancienneAgence = vehicule.getAgence();
        if (ancienneAgence != null && ancienneAgence.getVehicules() != null) {
            ancienneAgence.getVehicules().remove(vehicule);
        }
        vehicule.setAgence(nouvelleAgence);
        if (nouvelleAgence.getVehicules() == null) {
            nouvelleAgence.setVehicules(new HashSet<>());
        }
        nouvelleAgence.getVehicules().add(vehicule);

        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable: " + idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new EntityNotFoundException("Equipement introuvable: " + idEquipement));

        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new HashSet<>());
        }
        vehicule.getEquipements().add(equipement);
        if (equipement.getVehicules() == null) {
            equipement.setVehicules(new HashSet<>());
        }
        equipement.getVehicules().add(vehicule);

        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable: " + idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new EntityNotFoundException("Equipement introuvable: " + idEquipement));

        if (vehicule.getEquipements() != null) {
            vehicule.getEquipements().remove(equipement);
        }
        if (equipement.getVehicules() != null) {
            equipement.getVehicules().remove(vehicule);
        }

        return vehiculeRepository.save(vehicule);
    }
}
