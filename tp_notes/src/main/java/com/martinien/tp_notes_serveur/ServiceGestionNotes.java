package com.martinien.tp_notes_serveur;

import java.util.*;
import java.util.stream.Collectors;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class ServiceGestionNotes {
    private EtudiantRepository etudiantRepository;
    private int tailleMax;

    public ServiceGestionNotes(int tailleMax) {
        this.etudiantRepository = new EtudiantRepository();
        this.tailleMax = tailleMax;
    }

    public ServiceGestionNotes() {
        this(50);
    }

    @WebMethod
    public boolean ajouterEtudiant(Etudiant etudiant) {
        if (etudiantRepository.compterEtudiants() < tailleMax) {
            etudiantRepository.ajouterEtudiant(etudiant);
            return true;
        }
        return false;
    }

    @WebMethod
    public String getNote(String nom) {
        Etudiant etudiant = etudiantRepository.trouverParNom(nom);
        if (etudiant == null) return "Etudiant non trouvé";
        return etudiant.getNom() + " " + etudiant.getPrenom() + " a les notes suivantes:\n"
                + " note1=" + etudiant.getNote1()
                + " note2=" + etudiant.getNote2()
                + ", moyenne = " + etudiant.getNote() + ".";
    }

    @WebMethod
    public List<Etudiant> getEtudiantsValidant() {
        return etudiantRepository.trouverEtudiantsValidant();
    }

    @WebMethod
    public List<Etudiant> getEtudiantsMajorant() {
        return etudiantRepository.trouverEtudiantsMajorant();
    }

    @WebMethod
    public List<Etudiant> getEtudiantTries() {
        return etudiantRepository.trouverEtudiantsTries();
    }
    
    @WebMethod
    public List<Etudiant> getTousEtudiants() {
        return etudiantRepository.trouverTous();
    }

    public double getNoteMajorant() {
        List<Etudiant> majorants = etudiantRepository.trouverEtudiantsMajorant();
        if (majorants.isEmpty()) return 0.0;
        return majorants.get(0).getNote();
    }

    // Méthode pour fermer les ressources
    public void close() {
        if (etudiantRepository != null) {
            etudiantRepository.close();
        }
    }
}