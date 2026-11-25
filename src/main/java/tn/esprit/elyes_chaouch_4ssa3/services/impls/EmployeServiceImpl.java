package tn.esprit.elyes_chaouch_4ssa3.services.impls;

import lombok.AllArgsConstructor;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.elyes_chaouch_4ssa3.entities.Agence;
import tn.esprit.elyes_chaouch_4ssa3.reposetiries.EmployeReposotory;
import tn.esprit.elyes_chaouch_4ssa3.entities.Employe;
import tn.esprit.elyes_chaouch_4ssa3.reposetiries.AgenceReposotirie;
import tn.esprit.elyes_chaouch_4ssa3.services.interfaces.IEmployeService;

import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@Service
public class EmployeServiceImpl implements IEmployeService {
    EmployeReposotory employeRepository;
    AgenceReposotirie agenceRepository;

    @Override
    public Employe ajouterEmploye(Employe a) {
        return employeRepository.save(a);
    }

    @Override
    public Employe updateEmploye(Employe a) {
        return employeRepository.save(a);
    }

    @Override
    public void deleteEmploye(Long id) {
        employeRepository.deleteById(id);
    }

    @Override
    public Employe getEmploye(Long id) {
        return employeRepository.findById(id).get();
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe employe = employeRepository.findById(idEmploye)
                .orElseThrow(() -> new EntityNotFoundException("Employe introuvable: " + idEmploye));

        Agence ancienneAgence = employe.getAgence();
        employe.setAgence(null);
        if (ancienneAgence != null && ancienneAgence.getEmployes() != null) {
            ancienneAgence.getEmployes().remove(employe);
        }

        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = employeRepository.findById(idEmploye)
                .orElseThrow(() -> new EntityNotFoundException("Employe introuvable: " + idEmploye));
        if (employe.getAgence() != null) {
            throw new IllegalStateException("Desaffectez d'abord l'employe de son agence actuelle.");
        }

        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable: " + idAgence));
        employe.setAgence(agence);
        if (agence.getEmployes() == null) {
            agence.setEmployes(new HashSet<>());
        }
        agence.getEmployes().add(employe);

        return employeRepository.save(employe);
    }
}
