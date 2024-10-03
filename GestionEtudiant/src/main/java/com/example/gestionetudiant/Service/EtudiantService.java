package com.example.gestionetudiant.Service;

import com.example.gestionetudiant.Dto.FiliereDTO;
import com.example.gestionetudiant.Entity.Etudiant;
import com.example.gestionetudiant.Repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public EtudiantService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiantById(int id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public Etudiant saveEtudiant(Etudiant etudiant) {
        // Check if the Filiere exists
        FiliereDTO filiere = getFiliereById(etudiant.getFiliereId());
        if (filiere == null) {
            throw new IllegalArgumentException("Filiere with ID " + etudiant.getFiliereId() + " does not exist.");
        }

        return etudiantRepository.save(etudiant);
    }

    public void deleteEtudiant(int id) {
        etudiantRepository.deleteById(id);
    }

    public FiliereDTO getFiliereById(int filiereId) {
        String filiereServiceUrl = "http://localhost:8081/filieres/" + filiereId;
        return restTemplate.getForObject(filiereServiceUrl, FiliereDTO.class);
    }
}
