BookHub - Application de gestion et recherche des livres qui sont dans ma bibliothèques

Description 📚

BookHub est une application web de gestion et de recherche avancée de tous les livres que j'ai pu lire jusqu'a aujoudr'hui, construite avec Spring Boot, MySQL, et Elasticsearch. Elle propose des fonctionnalités CRUD pour gérer les livres, les utilisateurs et les critiques, ainsi qu'une recherche full-text optimisée avec Elasticsearch.

Fonctionnalités 🚀

Gestion des livres : Création, lecture, mise à jour et suppression (CRUD).

Gestion des utilisateurs : Enregistrement, authentification et gestion des comptes.

Sécurité avancée : Authentification avec JWT pour sécuriser les endpoints.

Critiques et notes : Ajout de commentaires et de notes sur les livres.

Recherche avancée : Recherche full-text avec filtres par titre, auteur et catégorie grâce à Elasticsearch.

Synchronisation avec MySQL : Importation manuelle des données de MySQL vers Elasticsearch.

Technologies Utilisées 🛠️

Backend : Java, Spring Boot

Base de données relationnelle : MySQL

Moteur de recherche : Elasticsearch

Authentification : Spring Security, JWT

Gestion des dépendances : Maven

Conteneurisation : Docker

Tests API : Postman

Prérequis ✅

Java 17+

Maven 3.8+

Docker Desktop ou MySQL Server

Elasticsearch 7.17+

Installation et Lancement 🚦

Étapes :

Cloner le dépôt :

git clone https://github.com/votre-utilisateur/BookHub.git

Configurer la base de données MySQL :
Lancer un conteneur Docker avec MySQL :

docker run --name name -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8

Créer une base de données bookHub.

Configurer Elasticsearch :
Lancer un conteneur Docker pour Elasticsearch :

docker run -d --name elasticsearch -p 9200:9200 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.17.13

Configurer l'application :
Mettre à jour le fichier src/main/resources/application.properties :

spring.datasource.url=jdbc:mysql://localhost:3306/bookHub
spring.datasource.username=root
spring.datasource.password=root
spring.elasticsearch.rest.uris=http://localhost:9200
spring.jpa.hibernate.ddl-auto=update

Construire et exécuter l'application :

mvn clean install
mvn spring-boot:run

Endpoints API 🔗

Authentification :

POST /api/auth/register - Crée un nouvel utilisateur.

POST /api/auth/login - Connecte un utilisateur et retourne un token JWT.

Livres :

GET /api/books - Liste tous les livres.

POST /api/books - Ajoute un livre (authentifié).

PUT /api/books/{id} - Met à jour un livre.

DELETE /api/books/{id} - Supprime un livre.

Critiques :

POST /api/reviews - Ajoute une critique.

GET /api/reviews/{id} - Récupère une critique.

Recherche Elasticsearch :

POST /api/search/reindex - Synchronise les données entre MySQL et Elasticsearch.

GET /api/search/books/search?title={titre} - Recherche par titre.

GET /api/search/books/filter?category={category} - Recherche par catégorie.

Tests avec Postman 🧪

Importer la collection Postman fournie (non incluse dans ce README).

Tester chaque endpoint avec les exemples JSON fournis.

Fonctionnalités Futures 🌟

Synchronisation automatique entre MySQL et Elasticsearch.

Frontend avec React pour une interface utilisateur dynamique.

Pagination et tri dans les résultats Elasticsearch.

Déploiement avec Kubernetes.
