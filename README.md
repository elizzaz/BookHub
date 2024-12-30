# BookHub - Gestion de Livres avec Recherche Elasticsearch

## Description 📖
BookHub est une application de gestion de livres qui permet d'effectuer des opérations CRUD sur une base de données MySQL et d'indexer automatiquement les données dans Elasticsearch pour permettre des recherches avancées.

---

## Fonctionnalités 🚀

### 1. Gestion des livres (CRUD)
- Création, lecture, mise à jour et suppression de livres.
- Validation des entrées utilisateur avec **Spring Boot Validation**.

### 2. Recherche avancée avec Elasticsearch 🔎
- Recherche par **titre** (recherche full-text).
- Recherche par **catégories** (correction simplifiée et fonctionnelle).

### 3. Sécurisation avec Spring Security 🔒
- Authentification avec **JWT**.
- Routes sécurisées pour les opérations Elasticsearch.

---

## Technologies utilisées 🛠️

- **Backend :** Java Spring Boot
- **Base de données :** MySQL (avec Docker)
- **Moteur de recherche :** Elasticsearch (avec Docker)
- **ORM :** Hibernate / JPA
- **Sécurité :** Spring Security avec JWT
- **Gestion des dépendances :** Maven
- **Tests API :** Postman

---

## Pré-requis ✅

- **Docker** installé.
- **Java 17+** installé.
- **Maven** installé.
- Elasticsearch et MySQL doivent être lancés via Docker.

---

## Installation 🛠️

1. Cloner le repository :
```bash
git clone https://github.com/votre-repo/bookhub.git
```

2. Démarrer MySQL et Elasticsearch avec Docker :
```bash
docker-compose up -d
```

3. Configurer l'application dans **`application.properties`** :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookHub
spring.datasource.username=root
spring.datasource.password=root
elasticsearch.host=localhost
elasticsearch.port=9200
```

4. Installer les dépendances et démarrer l'application :
```bash
mvn clean install
mvn spring-boot:run
```

---

## Routes API 🌐

### Authentification 🔐
- **Inscription :** `POST /api/auth/register`
- **Connexion :** `POST /api/auth/login`

### Gestion des livres 📚
- **Créer un livre :** `POST /api/books`
- **Obtenir tous les livres :** `GET /api/books`
- **Obtenir un livre par ID :** `GET /api/books/{id}`
- **Mettre à jour un livre :** `PUT /api/books/{id}`
- **Supprimer un livre :** `DELETE /api/books/{id}`

### Recherche Elasticsearch 🔎
- **Recherche par titre :** `GET /api/els/bytitle?title=<title>`
- **Recherche par catégories :** `GET /api/els/searchByCategories?categories=FICTION`
- **Réindexation complète :** `POST /api/els/reindex`

---

## Notes importantes ⚠️

- Les requêtes protégées nécessitent un **JWT valide** dans les headers :
```
Authorization: Bearer <token>
```
- Réindexer les données après toute modification dans MySQL :
```
POST /api/els/reindex
```

---

## Prochaines étapes 🛠️

- Ajouter la **pagination** et le **tri** dans les recherches Elasticsearch.
- Synchroniser automatiquement MySQL ↔ Elasticsearch avec **Logstash**.
- Tests unitaires pour sécuriser les fonctionnalités critiques.

---

## Auteur ✍️
Développé par Elizzaz.

