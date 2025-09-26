# 📌 Projet de Gestion d’Assurance (Application Console Java)

## 📖 Contexte
Une société d’assurance souhaite **digitaliser ses services**.  
L’objectif est de développer une **application console** qui permet de gérer efficacement :
- Les conseillers
- Les clients
- Les contrats
- Les sinistres

Le projet intègre les concepts modernes de **Java 8** :
- Stream API
- Expressions Lambda et Method References
- Optional
- Java Time API (gestion des dates)
- JDBC pour la persistance des données

---

## 🎯 Objectifs
- Créer une application console fonctionnelle pour gérer des assurés et leurs sinistres.
- Appliquer les principes de la **programmation fonctionnelle** avec Java 8.
- Mettre en place une **architecture en couches (MVC + DAO)** respectant la séparation des responsabilités.

---

## 🖥️ Interface Utilisateur (Menus)

### 📂 Gérer les conseillers
- Ajouter un conseiller (ID généré automatiquement).
- Supprimer un conseiller par ID.
- Rechercher un conseiller par ID.
- Afficher les clients d’un conseiller par son ID.

### 📂 Gérer les clients
- Ajouter un client (ID généré automatiquement).
- Supprimer un client par ID.
- Rechercher un client par nom de famille et trier par ordre alphabétique (**Stream API**).
- Rechercher un client par ID (**Optional**).
- Afficher les clients d’un conseiller par ID (**Stream API**).

### 📂 Gérer les contrats
- Ajouter un contrat (ID généré automatiquement, rattacher au client par son ID).
- Afficher les informations d’un contrat par ID (**Optional**).
- Supprimer un contrat par ID.
- Afficher les contrats souscrits d’un client par son ID.

### 📂 Gérer les sinistres
- Ajouter un sinistre (ID généré automatiquement, rattacher au contrat par son ID).
- Supprimer un sinistre par ID.
- Calculer le coût total des sinistres déclarés d’un client (**Stream API**).
- Rechercher un sinistre par ID (**Optional**).
- Afficher la liste des sinistres d’un contrat par ID (**Stream API**).
- Afficher les sinistres triés par montant décroissant (**Stream API**).
- Afficher les sinistres par ID client (**Stream API**).
- Afficher les sinistres avant une date donnée (**Stream API**).
- Afficher les sinistres dont le coût dépasse un montant donné (**Stream API**).

---

## 🏗️ Architecture du projet

### Couche **Model**
- `Person` : nom, prénom, email
- `Conseiller` (extends Person)
- `Client` (extends Person, relié à un Conseiller)
- `Contrat` : id, typeContrat (ENUM), dateDebut, dateFin, client
- `Sinistre` : id, typeSinistre (ENUM), dateTime, cout, description, contrat

### Couche **Enum**
- `TypeContrat` : automobile, maison, maladie
- `TypeSinistre` : accident voiture, accident maison, maladie

### Couche **DAO** (persistance avec JDBC)
- `ClientDAO`
- `ContratDAO`
- `SinistreDAO`

### Couche **Service** (logique métier)
- `ConseillerService`
- `ClientService`
- `ContratService`
- `SinistreService`

### Couche **View** (interaction console)
- `ClientView`
- `ContratView`
- `SinistreView`

---
### Prérequis
- Java 8+
- PostgreSQL (ou autre SGBD compatible JDBC)

