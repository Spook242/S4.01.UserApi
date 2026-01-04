# User API - Gestió d'Usuaris amb Spring Boot

## Descripció
Aquest projecte és una **API RESTful** desenvolupada amb Java i Spring Boot per a la gestió d'usuaris.
L'exercici se centra en l'aprenentatge progressiu, passant d'un controlador simple a una **Arquitectura per Capes (Controller, Service, Repository)** neta i escalable, aplicant principis **SOLID**.

**Funcionalitats principals:**
* Creació d'usuaris (amb validació de correu únic).
* Llistat d'usuaris (amb filtratge opcional per nom).
* Consulta d'usuari per ID (UUID).
* Gestió d'errors personalitzada (404 Not Found, 409 Conflict).
* Emmagatzematge en memòria (simulació de base de dades).

El projecte ha estat desenvolupat utilitzant **TDD (Test Driven Development)** per a la lògica de negoci i compta amb una **cobertura de tests del 100%**.

## Tecnologies Utilitzades
El projecte s'ha construït amb les següents tecnologies i eines:

* **Llenguatge:** Java 21
* **Framework:** Spring Boot 3.x (Spring Web)
* **Gestor de dependències:** Maven
* **Testing:**
    * JUnit 5
    * Mockito (Mocking de dependències)
    * Spring Boot Test (Tests d'integració amb MockMvc)
    * IntelliJ IDEA Coverage (per l'anàlisi de cobertura)
* **Format de dades:** JSON (Jackson)
* **Eines:** Git, Postman (per a proves manuals)

## Requisits
Abans de començar, assegura't de tenir instal·lat el següent:

* **Java JDK 21** o superior.
* **Maven** (opcional, el projecte inclou el wrapper `mvnw`).
* Un IDE compatible (IntelliJ IDEA recomanat, Eclipse o VS Code).
* **Git** per al control de versions.

## Instal·lació
Segueix aquests passos per configurar l'entorn local:

1.  **Clonar el repositori:**
    ```bash
    git clone <URL_DEL_TEU_REPOSITORI>
    cd userapi
    ```

2.  **Compilar el projecte i descarregar dependències:**
    ```bash
    ./mvnw clean install
    ```
    *(A Windows utilitza `mvnw.cmd clean install`)*

## Execució
Per aixecar el servidor localment:

1.  **Executar l'aplicació:**
    ```bash
    ./mvnw spring-boot:run
    ```

2.  **Verificar que funciona:**
    L'API estarà disponible a `http://localhost:9000` (o el port configurat).

### Endpoints per provar (Postman / cURL)

| Mètode | Endpoint | Descripció | Exemple Body (JSON) |
| :--- | :--- | :--- | :--- |
| **GET** | `/users` | Llistar tots els usuaris | - |
| **GET** | `/users?name=Ada` | Filtrar usuaris per nom | - |
| **GET** | `/users/{id}` | Obtenir usuari per UUID | - |
| **POST** | `/users` | Crear un nou usuari | `{"name": "Nom", "email": "correu@test.com"}` |

## Tests
Aquest projecte posa un fort èmfasi en la qualitat del codi. Per executar els tests automatitzats:

```bash
./mvnw test
