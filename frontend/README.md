# Application Frontend de Gestion des Produits et Commandes

Cette application Angular 18 permet de gérer les produits et les commandes avec une interface utilisateur moderne.

## Prérequis

- Node.js 18+
- npm 9+
- Angular CLI 18

## Installation et démarrage

1. Cloner le dépôt
2. Naviguer vers le dossier frontend
3. Installer les dépendances :

```bash
npm install
```

4. Démarrer l'application en mode développement :

```bash
ng serve
```

L'application sera disponible à l'adresse : `http://localhost:4200`

## Fonctionnalités

### Gestion des produits
- Visualisation de tous les produits groupés par catégorie
- Affichage détaillé d'un produit spécifique

### Gestion des commandes
- Création d'une nouvelle commande
- Validation de la disponibilité des produits standards
- Vérification des délais minimums pour les produits sur commande
- Confirmation de commande

## Structure du projet

```
src/
├── app/
│   ├── core/
│   │   ├── models/
│   │   ├── services/
│   │   └── interceptors/
│   ├── shared/
│   │   ├── components/
│   │   └── pipes/
│   └── features/
│       ├── produits/
│       └── commandes/
├── assets/
└── environments/
```

## Technologies utilisées

- Angular 18
- TypeScript
- RxJS
- Tailwind CSS
- Angular Router
- Angular Forms
