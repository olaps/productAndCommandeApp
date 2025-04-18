-- Standard products
INSERT INTO produit (id, nom, reference, categorie, prix, stock)
VALUES (1, 'Écran 24 pouces', 'ECR24-2023', 'STANDARD', 199.99, 10);

INSERT INTO produit (id, nom, reference, categorie, prix, stock)
VALUES (2, 'Clavier mécanique', 'KEY-MEC-2023', 'STANDARD', 89.99, 15);

INSERT INTO produit (id, nom, reference, categorie, prix, stock)
VALUES (3, 'Souris sans fil', 'MOU-WL-2023', 'STANDARD', 49.99, 20);

-- Special order products
INSERT INTO produit (id, nom, reference, categorie, prix, delai_livraison)
VALUES (4, 'Bureau sur mesure', 'BUR-CUSTOM-2023', 'SUR_COMMANDE', 349.99, 15);

INSERT INTO produit (id, nom, reference, categorie, prix, delai_livraison)
VALUES (5, 'Chaise ergonomique personnalisée', 'CHA-ERGO-2023', 'SUR_COMMANDE', 249.99, 10);

INSERT INTO produit (id, nom, reference, categorie, prix, delai_livraison)
VALUES (6, 'Station de travail complète', 'WRK-STATION-2023', 'SUR_COMMANDE', 999.99, 20);