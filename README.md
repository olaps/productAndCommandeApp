# Test Technique Développeur Java/Angular

## Objectif
Créer une application de gestion de produits et commandes composée d'un backend en Java 17 et d'un frontend en Angular 18.
Créer une branche à votre nom et prénom (ex : test/arthur-josseau ) et push sur ce repo. 
L'utilisation de l'intelligence artificielle est autorisée. 

## Livraison attendue

- Code source complet (backend et frontend)
- Que les projets compilent. Il n'est pas nécessaire qu'ils fonctionnent. (Inutile d'implémenter un H2 ou une base de donnée, inutile de tester si le projet démarre, possibilité de mock pour le backend ou une implémentation de data jpa repository, inutile de passer du temps sur l'intégration entre le back et le front, les contrats d'interfaces et les api suffisent. )
- Vous serez jugé sur la pertinence des implémentations au vu du temps impartie ( balance qualité / délai ) et sur la quantité de points validés ci-dessous. 
- Aucunes architectures spécifiques n'est imposée. 
- Aucunes bonnes pratiques spécifiques n'est imposée. 

## Technologies requises
- **Backend** : Java 17, Spring Boot
- **Frontend** : Angular 18, tailwind

## Architecture générale
L'application sera composée de deux parties distinctes :
- Un web service RESTful en Java 17 (dossier backend)
- Une application frontend en Angular 18 (dossier frontend)

## Implémentation actuelle
- Un projet backend avec Spring Boot et la dépendance starter web
  - API CRUD pour les produits implémentée (voir détails ci-dessous)
  - Stockage en mémoire (sans base de données)
- Un projet frontend vide est déjà à disposition avec Angular 18 et la librairie tailwind

### API Produit implémentée
Une API CRUD complète pour la gestion des produits a été implémentée dans le backend:

- **GET /api/produits** - Récupérer tous les produits
- **GET /api/produits/{id}** - Récupérer un produit par son ID
- **GET /api/produits/grouped-by-categorie** - Récupérer tous les produits groupés par catégorie
- **POST /api/produits** - Créer un nouveau produit
- **PUT /api/produits/{id}** - Mettre à jour un produit existant
- **DELETE /api/produits/{id}** - Supprimer un produit

Pour plus de détails sur l'API, consultez le fichier [backend/README.md](backend/README.md).

## Spécifications fonctionnelles

### 1. Gestion des produits

#### Catégories de produits
- **Produit standard** : Produit disponible à la vente immédiate
- **Produit sur commande** : Produit nécessitant un délai de fabrication

#### Structure de données d'un produit
| Champ       | Type        | Description                                                       |
| ----------- | ----------- | ----------------------------------------------------------------- |
| id          | number      | Identifiant unique du produit                                     |
| nom         | String      | Nom du produit                                                    |
| reference   | String      | Référence unique du produit                                       |
| categorie   | Enum        | STANDARD ou SUR_COMMANDE                                          |
| prix        | Decimal     | Prix unitaire du produit                                          |
| delaiLivraison | Integer  | Uniquement pour produits sur commande, délai en jours            |
| stock | Integer  | Uniquement pour produits  standard            |

### 2. Fonctionnalités à implémenter

#### Visualisation des produits
- **Liste des produits** : Afficher tous les produits, regrouper les produits par catégorie (standard/sur commande)
- **Vue détaillée** : Page dédiée pour afficher les détails d'un produit spécifique

#### Gestion des commandes
- **Sélection des produits** : 
  - Produits à commander
  - Quantités pour chaque produit
- **Paramètres de la commande** : 
  - Date de livraison souhaitée
  - Adresse de livraison
  - Mode de paiement (comptant, différé)
- **Validations** :
  - Vérification de la disponibilité des produits standards
  - Respect des délais minimums pour les produits sur commande

#### Gestion des erreurs
- **Format des erreurs** : 
  - Code d'erreur codifié (ex: ER012345)
  - Message descriptif
- **Types d'erreurs à gérer** :
  - Produit indisponible
  - Délai de livraison impossible
  - Quantité invalide
  - Erreurs de validation des données (produit inexistant, référence incorrecte)

## Spécifications techniques

### Backend (Java 17)

#### Exemple de structure de réponse pour les produits standards
```json
[
  {
  "id": 12345678,
  "nom": "Écran 24 pouces",
  "reference": "ECR24-2023",
  "categorie": "STANDARD",
  "prix": 199.99,
  "stock": 10
}
]
```

#### Exemple de structure de réponse pour les produits sur commande
```json
[{
  "id": 98765432,
  "nom": "Bureau sur mesure",
  "reference": "BUR-CUSTOM-2023",
  "categorie": "SUR_COMMANDE",
  "prix": 349.99,
  "delaiLivraison": 15
}]
```

#### Exemple de structure pour la requête de commande ( les dates peuvent être des timestamps ou des dates )
```json
{
  "produitId": 12345678,
  "quantite": 2,
  "dateLivraisonSouhaitee": "2025-05-15",
  "adresseLivraison": "123 rue des Lilas, 75001 Paris",
  "modePaiement": "COMPTANT"
}
```

#### Structure des réponses d'erreur
```json
{
  "code": "ER012345",
  "message": "Délai de livraison impossible pour le produit Bureau sur mesure"
}
```

### Frontend (Angular 18)

#### Pages à implémenter
1. **Liste des produits** - Affichage de tous les produits regroupés par catégorie
2. **Détail d'un produit** - Affichage détaillé des informations d'un produit
3. **Formulaire de commande** - Interface pour effectuer une commande

#### Fonctionnalités UI requises
- Navigation entre les pages
- Validation côté client
- Affichage des messages d'erreur
- Gestion des états de chargement (loader)


## Bonus (optionnel)

- Authentification des utilisateurs
- Historique des commandes
- Un parnier avec commande groupé. 
- Filtres et recherche dans la liste des produits
- Tests unitaires
- Documentation API (ex : swagger)
