package com.example.gestionetudiant.Repository;

import com.example.gestionetudiant.Entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {}
