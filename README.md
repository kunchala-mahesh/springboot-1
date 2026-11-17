# Spring Boot Basic REST API Application

A complete, standalone Spring Boot application implementing RESTful CRUD APIs for `/posts`, `/products`, and `/users`.

---

## 🚀 Getting Started

### Prerequisites
- Java 21 LTS
- Maven 3.9+ (or use the included `./mvnw` wrapper)

### Running the Application

From this project directory:

```bash
# Using Maven
mvn spring-boot:run

# Or using Maven wrapper (Windows PowerShell/CMD)
.\mvnw.cmd spring-boot:run

# Or using Maven wrapper (Linux/macOS)
./mvnw spring-boot:run
```

The application will start on **http://localhost:8080**.

---

## 📌 Available Endpoints

### 1. Root / Health Check
- `GET /` - Overview and API discovery.

---

### 2. Users API (`/users`)
Pre-seeded with initial users.

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/users` | List all users |
| `GET` | `/users/{id}` | Get user by ID |
| `POST` | `/users` | Create a new user |
| `PUT` | `/users/{id}` | Update existing user |
| `DELETE` | `/users/{id}` | Delete user |

#### Example Request: Create User
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "email": "jane@example.com",
    "role": "DEVELOPER"
  }'
```

---

### 3. Products API (`/products`)
Pre-seeded with initial electronic products.

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/products` | List all products |
| `GET` | `/products/{id}` | Get product by ID |
| `POST` | `/products` | Create a new product |
| `PUT` | `/products/{id}` | Update existing product |
| `DELETE` | `/products/{id}` | Delete product |

#### Example Request: Create Product
```bash
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Noise Cancelling Headphones",
    "description": "Over-ear Bluetooth headphones with active noise cancellation",
    "price": 149.99,
    "stock": 25
  }'
```

---

### 4. Posts API (`/posts`)
Pre-seeded with sample blog/community posts.

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/posts` | List all posts |
| `GET` | `/posts?authorId={id}` | Filter posts by author ID |
| `GET` | `/posts/{id}` | Get post by ID |
| `POST` | `/posts` | Create a new post |
| `PUT` | `/posts/{id}` | Update existing post |
| `DELETE` | `/posts/{id}` | Delete post |

#### Example Request: Create Post
```bash
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Spring Boot 3 & Java 21",
    "content": "Building scalable web services with modern Java features.",
    "authorId": 1
  }'
```

---

## 🛠️ Features Included
- **Thread-safe In-Memory Storage**: Uses `ConcurrentHashMap` with atomic ID generation for fast out-of-the-box testing without requiring an external database setup.
- **Validation**: Jakarta validation annotations (`@NotBlank`, `@Email`, `@NotNull`, `@Min`, `@DecimalMin`) with a centralized `GlobalExceptionHandler` returning structured JSON validation error details.
- **Seed Data**: Pre-populated records for immediate testing right after boot.
