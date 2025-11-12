# Projet Web Service - Gestion des Notes Étudiants

## Contexte
Ce projet s'inscrit dans le cadre du cours de Web Services du dernier semestre du cycle d'ingénieur à l'ENSA Fès. L'objectif est de maîtriser les concepts des services web SOAP et la génération automatique de code via WSDL.

## Objectifs Pédagogiques
- Comprendre le fonctionnement des WSDL dans les services web SOAP
- Maîtriser la génération automatique de code client à partir des définitions WSDL
- Implémenter une architecture client-serveur avec services web
- Utiliser JPA pour la persistance des données
- Déployer une base de données avec Docker
- Développer une interface client conviviale avec Swing

## Architecture du Projet

### Backend (Serveur)
- **Service Web SOAP** : Implémentation JAX-WS avec annotations `@WebService`
- **Persistence** : JPA/Hibernate avec PostgreSQL
- **Base de données** : Conteneur Docker PostgreSQL
- **Fonctionnalités** :
  - Ajout d'étudiants
  - Consultation des notes
  - Liste des étudiants validants (moyenne ≥ 12)
  - Identification des majorants
  - Tri des étudiants par moyenne

### Frontend (Client)
- **Interface Graphique** : Application Swing
- **Génération automatique** : Classes clientes générées à partir du WSDL
- **Fonctionnalités** :
  - Interface intuitive pour interagir avec le service
  - Affichage des résultats sous forme tabulaire
  - Gestion des erreurs utilisateur

## Technologies Utilisées
- **Java 17+** avec JAX-WS
- **JPA/Hibernate** pour l'ORM
- **PostgreSQL** base de données
- **Docker** pour le déploiement de la BDD
- **Swing** pour l'interface client
- **Maven** pour la gestion des dépendances

## Structure du Projet
