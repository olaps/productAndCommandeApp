# Application de Gestion de Produits et Commandes

Ce projet est une application complète de gestion de produits et commandes avec un backend Java 17/Spring Boot et un frontend Angular 18. L'application permet de gérer différentes catégories de produits (standards et sur commande) et de passer des commandes avec validation des règles métier.

## Structure du projet

Le projet est divisé en deux parties principales :

```
/
├── backend/       # API REST en Java 17 et Spring Boot
└── frontend/      # Interface utilisateur en Angular 18
```

## Fonctionnalités

- Liste des produits regroupés par catégorie
- Détail d'un produit spécifique
- Formulaire de commande avec validation des contraintes métier
- Gestion des stocks pour les produits standards
- Gestion des délais de livraison pour les produits sur commande
- Validation complète des données saisies
- Gestion des erreurs standardisée

## Technologies utilisées

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Validation
- Lombok
- Springdoc OpenAPI (Swagger)
- H2 Database

### Frontend
- Angular 18
- TypeScript
- Tailwind CSS
- Angular Forms
- Standalone Components

## Installation et démarrage

### Prérequis
- Java 17
- Node.js 18+
- npm 9+
- Maven 3.8+

### Backend
1. Naviguer vers le dossier backend
2. Exécuter :
```bash
mvn spring-boot:run
```
L'API sera accessible à l'adresse : http://localhost:8080

Documentation Swagger disponible à : http://localhost:8080/swagger-ui/index.html

### Frontend
1. Naviguer vers le dossier frontend
2. Installer les dépendances :
```bash
npm install
```
3. Démarrer l'application :
```bash
ng serve
```
L'application sera accessible à l'adresse : http://localhost:4200

## Détails techniques

### Backend

#### Structure du projet backend
```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/gestionproduits/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   └── test/
└── pom.xml
```

#### API REST endpoints
- `GET /api/produits` - Liste des produits
- `GET /api/produits/{id}` - Détail d'un produit
- `GET /api/produits/grouped-by-categorie` - Produits groupés par catégorie
- `POST /api/produits` - Créer un produit
- `PUT /api/produits/{id}` - Mettre à jour un produit
- `DELETE /api/produits/{id}` - Supprimer un produit
- `POST /api/commandes` - Créer une commande
- `GET /api/commandes` - Liste des commandes
- `GET /api/commandes/{id}` - Détail d'une commande

### Frontend

#### Structure du projet frontend
```
frontend/
├── src/
│   ├── app/
│   │   ├── core/
│   │   │   ├── models/
│   │   │   ├── services/
│   │   │   └── interceptors/
│   │   ├── shared/
│   │   │   ├── components/
│   │   │   └── pipes/
│   │   └── features/
│   │       ├── produits/
│   │       └── commandes/
│   ├── assets/
│   └── environments/
└── angular.json
```

#### Routes principales
- `/produits` - Liste des produits
- `/produits/:id` - Détail d'un produit
- `/commander/:produitId` - Formulaire de commande
- `/confirmation` - Confirmation de commande

## Règles métier implémentées

- Les produits standards ont un stock qui diminue lors des commandes
- Les produits sur commande ont un délai de fabrication
- Les commandes de produits standards vérifient la disponibilité en stock
- Les commandes de produits sur commande vérifient le délai minimum de livraison
- Les dates de livraison sont validées selon le type de produit et ses contraintes

## Remarques

- L'application utilise une base de données H2 en mémoire pour simplifier le développement
- Des données de test sont chargées au démarrage pour faciliter les tests
- L'architecture frontend utilise les composants standalone d'Angular 18
- Le design est responsive grâce à Tailwind CSS