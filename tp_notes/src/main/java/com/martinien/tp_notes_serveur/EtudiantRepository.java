package com.martinien.tp_notes_serveur;

import jakarta.persistence.*;
import java.util.List;

public class EtudiantRepository {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public EtudiantRepository() {
        this.emf = Persistence.createEntityManagerFactory("etudiant_pu");
        this.em = emf.createEntityManager();
    }
    
    public void ajouterEtudiant(Etudiant etudiant) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(etudiant);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
    
    public Etudiant trouverParNom(String nom) {
        try {
            return em.createQuery("SELECT e FROM Etudiant e WHERE e.nom = :nom", Etudiant.class)
                    .setParameter("nom", nom)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<Etudiant> trouverTous() {
        return em.createQuery("SELECT e FROM Etudiant e", Etudiant.class)
                .getResultList();
    }
    
    public List<Etudiant> trouverEtudiantsValidant() {
        return em.createQuery("SELECT e FROM Etudiant e WHERE (e.note1 + e.note2)/2 >= 12", Etudiant.class)
                .getResultList();
    }
    
    public List<Etudiant> trouverEtudiantsMajorant() {
        return em.createQuery("SELECT e FROM Etudiant e WHERE (e.note1 + e.note2)/2 = " +
                "(SELECT MAX((e2.note1 + e2.note2)/2) FROM Etudiant e2)", Etudiant.class)
                .getResultList();
    }
    
    public List<Etudiant> trouverEtudiantsTries() {
        return em.createQuery("SELECT e FROM Etudiant e ORDER BY (e.note1 + e.note2)/2 DESC", Etudiant.class)
                .getResultList();
    }
    
    public long compterEtudiants() {
        return em.createQuery("SELECT COUNT(e) FROM Etudiant e", Long.class)
                .getSingleResult();
    }
    
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}