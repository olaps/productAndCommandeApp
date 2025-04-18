# API de Gestion des Produits et Commandes

Ce projet est une API REST développée avec Java 17 et Spring Boot pour la gestion des produits et des commandes.

## Prérequis

- Java 17
- Maven 3.8+

## Installation et démarrage

1. Cloner le dépôt
2. Naviguer vers le dossier backend
3. Exécuter la commande Maven :

```bash
mvn spring-boot:run
```

L'application sera disponible à l'adresse : `http://localhost:8080`

## Base de données

Le projet utilise H2, une base de données en mémoire pour le développement.
- Console H2 : `http://localhost:8080/h2-console`
- JDBC URL : `jdbc:h2:mem:testdb`
- Username : `sa`
- Password : `password`

## Documentation API

La documentation Swagger de l'API est disponible à l'adresse :
**http://localhost:8080/swagger-ui/index.html#/**

## Fonctionnalités

### Gestion des produits
- Liste de tous les produits (`GET /api/produits`)
- Détail d'un produit (`GET /api/produits/{id}`)
- Produits groupés par catégorie (`GET /api/produits/grouped-by-categorie`)
- Création d'un produit (`POST /api/produits`)
- Mise à jour d'un produit (`PUT /api/produits/{id}`)
- Suppression d'un produit (`DELETE /api/produits/{id}`)

### Gestion des commandes
- Liste des commandes (`GET /api/commandes`)
- Détail d'une commande (`GET /api/commandes/{id}`)
- Création d'une commande (`POST /api/commandes`)

## Structure du projet

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── gestionproduits/
│   │               ├── GestionProduitsApplication.java
│   │               ├── config/
│   │               ├── controller/
│   │               ├── dto/
│   │               ├── exception/
│   │               ├── model/
│   │               ├── repository/
│   │               └── service/
│   └── resources/
│       ├── application.properties
│       └── data.sql
└── test/
    └── java/
```

## Technologies utilisées

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Validation
- Lombok
- Springdoc OpenAPI (Swagger)
- H2 Database

## @Developpeur
Ismail EL BOURKHISSI