package com.martinien.tp_notes_serveur;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "etudiants")
public class Etudiant implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;
    
    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;
    
    @Column(name = "note1", nullable = false)
    private double note1;
    
    @Column(name = "note2", nullable = false)
    private double note2;
    
    // Constructeurs
    public Etudiant() {
    	
    }
    
    public Etudiant(String nom, String prenom, double note1, double note2) {
        this.nom = nom;
        this.prenom = prenom;
        this.note1 = note1;
        this.note2 = note2;
    }
    
    public Etudiant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.note1 = 0.0;
        this.note2 = 0.0;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public double getNote1() {
        return note1;
    }
    
    public void setNote1(double note1) {
        this.note1 = note1;
    }
    
    public double getNote2() {
        return note2;
    }
    
    public void setNote2(double note2) {
        this.note2 = note2;
    }
    
    @Transient // Cette annotation indique que ce champ n'est pas persisté en base
    public double getNote() {
        return (this.getNote1() + this.getNote2())/2;
    }
    
    @Override
    public String toString() {
        return String.format("Etudiant [id=%d, nom=%s, prenom=%s, note1=%.2f, note2=%.2f, moyenne=%.2f]",
                             id, nom, prenom, note1, note2, getNote());
    }
}