package com.soap.ws.client.generated;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServiceGestionNotesService s_service = new ServiceGestionNotesService();
		ServiceGestionNotes service = s_service.getServiceGestionNotesPort();
		
		JFrame jfm = new JFrame();
		jfm.setSize(900, 800);
		jfm.setLayout(new BorderLayout());
		JPanel panelWest = new JPanel();
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
		
		//infos de nom
		JPanel infoNom = new JPanel();
		JLabel labelNom = new JLabel("Nom: ");
		JTextArea nomArea = new JTextArea(1, 10);
		nomArea.setBackground(Color.green);
		infoNom.add(labelNom);
		infoNom.add(nomArea);
		panelWest.add(infoNom);
		
		
		//infos de prenom
		JPanel infoPrenom = new JPanel();
		JLabel labelPrenom = new JLabel("Prenom: ");
		JTextArea prenomArea = new JTextArea(1, 10);
		prenomArea.setBackground(Color.green);
		infoPrenom.add(labelPrenom);
		infoPrenom.add(prenomArea);
		panelWest.add(infoPrenom);
		
		//infos note1
		JPanel infoNote1 = new JPanel();
		JLabel labelNote1 = new JLabel("Note1: ");
		JTextArea note1Area = new JTextArea(1, 10);
		note1Area.setBackground(Color.green);
		infoNote1.add(labelNote1);
		infoNote1.add(note1Area);
		panelWest.add(infoNote1);
		
		//infos note2
		JPanel infoNote2 = new JPanel();
		JLabel labelNote2 = new JLabel("Note2: ");
		JTextArea note2Area = new JTextArea(1, 10);
		note2Area.setBackground(Color.green);
		infoNote2.add(labelNote2);
		infoNote2.add(note2Area);
		panelWest.add(infoNote2);
		
		
		//Bouton ajouterEtudiant
		JButton buttonAjouterEtudiant = new JButton("ajouter");
		buttonAjouterEtudiant.setBackground(new Color(100, 180, 100));
		buttonAjouterEtudiant.setForeground(Color.WHITE);
		buttonAjouterEtudiant.setFont(new Font("SansSerif", Font.BOLD, 16));
		buttonAjouterEtudiant.setFocusPainted(false);
		buttonAjouterEtudiant.setBorderPainted(false);
		buttonAjouterEtudiant.setOpaque(true);
		buttonAjouterEtudiant.setMargin(new Insets(10, 20, 10, 20));
		panelWest.add(buttonAjouterEtudiant);
		
		
		//voir la note d'un etudiant
		JPanel panelNord = new JPanel();
		JLabel labelNomReche = new JLabel("Nom: ");
		JTextArea nomAreaRech = new JTextArea(1, 10);
		nomAreaRech.setBackground(Color.ORANGE);
		JButton buttonRechNote = new JButton("Chercher");
		panelNord.add(labelNomReche);
		panelNord.add(nomAreaRech);
		panelNord.add(buttonRechNote);
		
		//boutons sud
		JPanel panelSud = new JPanel();
		JButton buttonGetEtudiantsValidant = new JButton("GetEtudiansValidant");
		JButton buttonGetTries = new JButton("GetEtudiantsTries");
		JButton buttonGetMajorant = new JButton("GetMajorants");
		panelSud.add(buttonGetEtudiantsValidant);
		panelSud.add(buttonGetTries);
		panelSud.add(buttonGetMajorant);
		
		//panel central pour l'affichage des elements
		JPanel panelCentral = new JPanel();
		JTextArea affichage = new JTextArea();
		//settings affichage:
        affichage.setBackground(new Color(255, 228, 240));
        affichage.setFont(new Font("Consolas", Font.PLAIN, 20));
        affichage.setMargin(new Insets(10, 1, 10, 10));
        affichage.setEditable(false);
        affichage.setWrapStyleWord(true);
        affichage.setLineWrap(true); // permet d’aller à la ligne automatiquement

        // Ajout du scroll
        JScrollPane scrollPane = new JScrollPane(affichage);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelCentral.setLayout(new BorderLayout());
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		
		
		//configuration des boutons
		buttonAjouterEtudiant.addActionListener(e->{
			Etudiant etudiant = new Etudiant();
			String nom = nomArea.getText();
			String prenom = prenomArea.getText();
			String note1_s = note1Area.getText();
			String note2_s = note2Area.getText();
			if(nom.isEmpty() || prenom.isEmpty() || note1_s.isEmpty() || note2_s.isEmpty())
			{
				JOptionPane.showMessageDialog(jfm, "tous les champs doivent etre instanciés");
				return;
			}
			try
			{
				double note1 = Double.parseDouble(note1_s);
				double note2 = Double.parseDouble(note2_s);
				etudiant.setNom(nom);
				etudiant.setPrenom(prenom);
				etudiant.setNote1(note1);
				etudiant.setNote2(note2);
				service.ajouterEtudiant(etudiant);
				JOptionPane.showMessageDialog(jfm, "Etudiant ajouté avec succes");
				nomArea.setText("");
                prenomArea.setText("");
                note1Area.setText("");
                note2Area.setText("");
				
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(jfm, "Une erreur s'est produite: " + err.getMessage());
				return;
			}
		});
		
		 // Chercher la note d’un étudiant
		buttonRechNote.addActionListener(e -> {
		    String nom = nomAreaRech.getText().trim();
		    if (nom.isEmpty()) {
		        JOptionPane.showMessageDialog(jfm, "Veuillez saisir un nom !");
		        return;
		    }

		    try {
		        // Essaye directement
		        String note = service.getNote(nom);

		        if (note == null) {
		            affichage.setText("Aucun étudiant trouvé avec le nom : " + nom);
		        } else {
		            affichage.setText(note);
		        }
		    } catch (Exception err) {
		        JOptionPane.showMessageDialog(jfm, "Erreur : " + err.getMessage());
		    }
		});
        
        //étudiants validants
        buttonGetEtudiantsValidant.addActionListener(e -> {
            try {
                List<Etudiant> etudiants = service.getEtudiantsValidant();
                StringBuilder sb = new StringBuilder("Liste des étudiants validants :\n");
                for (Etudiant et : etudiants) {
                    sb.append(et.getNom()).append(" ").append(et.getPrenom())
                      .append(" - Moyenne : ").append((et.getNote1() + et.getNote2()) / 2).append("\n");
                }
                affichage.setText(sb.toString());
            } catch (Exception err) {
                JOptionPane.showMessageDialog(jfm, "Erreur : " + err.getMessage());
            }
        });
        
     // Étudiants triés par note
        buttonGetTries.addActionListener(e -> {
            try {
                List<Etudiant> etudiants = service.getEtudiantTries();
                StringBuilder sb = new StringBuilder("Étudiants triés par note :\n");
                for (Etudiant et : etudiants) {
                    sb.append(et.getNom()).append(" ").append(et.getPrenom())
                      .append(" - Moyenne : ").append((et.getNote1() + et.getNote2()) / 2).append("\n");
                }
                affichage.setText(sb.toString());
            } catch (Exception err) {
                JOptionPane.showMessageDialog(jfm, "Erreur : " + err.getMessage());
            }
        });

        // Majorants
        buttonGetMajorant.addActionListener(e -> {
            try {
                List<Etudiant> majors = service.getEtudiantsMajorant();
                StringBuilder sb = new StringBuilder("Liste des majorants :\n");
                for (Etudiant et : majors) {
                    sb.append(et.getNom()).append(" ").append(et.getPrenom())
                      .append(" - Moyenne : ").append((et.getNote1() + et.getNote2()) / 2).append("\n");
                }
                affichage.setText(sb.toString());
            } catch (Exception err) {
                JOptionPane.showMessageDialog(jfm, "Erreur : " + err.getMessage());
            }
        });
        
        

		
		
		
		
		//affichage de la jframe
		jfm.add(panelWest, BorderLayout.WEST);
		jfm.add(panelNord, BorderLayout.NORTH);
		jfm.add(panelSud, BorderLayout.SOUTH);
		jfm.add(panelCentral, BorderLayout.CENTER);
		jfm.setVisible(true);
	}

}