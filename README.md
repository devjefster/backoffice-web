# Back Office Server (Backend)


- **Back Office Server (Backend)**: Java Spring Boot API handling business logic and data persistence.

# Back Office Server (Backend)
### Technologies Used
- Java 21
- Spring Boot 3
- PostgreSQL
- Hibernate
- Docker

### Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/your-org/back-office-server.git
   cd back-office-server
   ```
2. Install dependencies and build:
   ```sh
   ./mvnw clean install
   ```
3. Run the application:
   ```sh
   ./mvnw spring-boot:run
   ```

### Environment Variables
Create an `.env` file in the `back-office-server` directory:
```
DATABASE_URL=jdbc:postgresql://localhost:5432/isadora
DATABASE_USER=admin
DATABASE_PASSWORD=mypassword
AUTH_SERVER_URL=http://localhost:8081
```

