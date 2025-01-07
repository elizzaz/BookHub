📚 BookHub

BookHub est une application de gestion de livres avec des fonctionnalités avancées de recherche intégrée grâce à Elasticsearch et Spring Boot. 🚀

✨ Fonctionnalités

📖 Gestion des livres (CRUD) : Ajouter, modifier, supprimer et consulter des livres.

🔍 Recherche avancée avec Elasticsearch :

🔎 Recherche par titre avec pagination.

🗂️ Recherche par catégories avec filtres exacts.

📝 Recherche full-text sur plusieurs champs (titre, auteur, description).

🔒 Sécurité avec JWT :

🔑 Authentification avec tokens JWT.

🛡️ Endpoints sécurisés pour la gestion des livres et la recherche.

📊 Pagination et tri dynamique :

📚 Gestion des grandes quantités de données.

📌 Résultats triés par champ spécifié.

🔄 Synchronisation MySQL ↔ Elasticsearch :

📤 Indexation automatique des livres dans Elasticsearch lors des opérations CRUD.

🛠️ Technologies utilisées

Backend : Java, Spring Boot (JPA, Security).

Base de données : MySQL (Docker).

Recherche avancée : Elasticsearch (via RestHighLevelClient).

Sécurité : JWT pour l'authentification.

Tests API : Postman.

🚀 Installation

Clonez ce repository :

git clone https://github.com/votre-utilisateur/BookHub.git

Configurez la base de données MySQL via Docker :

docker run --name bookHub -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8

Lancez Elasticsearch via Docker :

docker run -d --name elasticsearch -p 9200:9200 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:8.6.2

Configurez les paramètres dans application.properties :

spring.datasource.url=jdbc:mysql://localhost:3306/bookHub
spring.datasource.username=root
spring.datasource.password=root
elasticsearch.host=localhost
elasticsearch.port=9200

Lancez l'application :

./mvnw spring-boot:run

🔗 Endpoints API

🔑 Authentification

📝 Register :
POST /api/auth/register
Body : JSON avec username, email, password.

🔓 Login :
POST /api/auth/login
Body : JSON avec username, password.

📘 Livres (CRUD)

➕ Créer un livre :
POST /api/books
Body : JSON avec titre, auteur, description, catégories, etc.

✏️ Modifier un livre :
PUT /api/books/{id}

🗑️ Supprimer un livre :
DELETE /api/books/{id}

📚 Lister tous les livres :
GET /api/books

🔍 Recherche avec Elasticsearch

🔎 Recherche par titre :
GET /api/els/bytitle?title=thriller&page=0&size=5

🗂️ Recherche par catégories :
GET /api/els/searchByCategories?categories=FICTION,THRILLER&page=0&size=5

📝 Recherche full-text :
GET /api/els/fulltext?query=thriller&page=0&size=5

🧪 Tests

✔️ Les tests ont été réalisés avec Postman pour valider toutes les fonctionnalités.

⚠️ Les erreurs serveur et les réponses vides sont gérées proprement avec des codes HTTP adaptés (204, 404, 500).

🔮 Prochaines étapes

🔧 Ajouter des filtres supplémentaires (langue, disponibilité).

🧪 Tests unitaires pour chaque méthode critique.

📄 Creation d'un front, conteneurisation et déploiement

👩‍💻 Auteurs

Développé par Elizzaz 💻

Supporté par Spring Boot et Elasticsearch 🚀.

