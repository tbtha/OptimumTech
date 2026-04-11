# 📚 OptimumTech

API REST desarrollada con Spring Boot.

---

## 📦 Tecnologías utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- MySQL
- Springdoc OpenAPI (Swagger)
- Lombok

---

## 🚀 Cómo ejecutar el proyecto

### Requisitos:

- JDK 17
- Maven
- MySQL (Puerto 3306)
## 📖 Documentación Swagger

Una vez que la aplicación esté corriendo, puedes acceder a la documentación interactiva de la API a través de Swagger UI:
## api course
👉 [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)
## api user
👉 [http://localhost:8081/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

También puedes obtener el esquema JSON de la API siguiendo este enlace:
## api course
👉 [http://localhost:8082/v3/api-docs](http://localhost:8082/v3/api-docs)
## api user
👉 [http://localhost:8081/v3/api-docs](http://localhost:8082/v3/api-docs)

---

A continuación, se detallan los endpoints disponibles en la API:

### API de Usuario

| Método | Endpoint           | Descripción                             |
|--------|--------------------|-----------------------------------------|
| PUT    | `/usuario`          | Actualizar un usuario                  |
| GET    | `/usuario/`         | Obtener todos los usuarios             |
| POST   | `/usuario/`         | Registrar un nuevo usuario             |
| GET    | `/usuario/{id}`     | Obtener un usuario por ID              |
| DELETE | `/usuario/{id}`     | Eliminar un usuario por ID             |

### API de Contenido

| Método | Endpoint           | Descripción                             |
|--------|--------------------|-----------------------------------------|
| GET    | `/contenido`        | Listar todos los contenidos             |
| PUT    | `/contenido`        | Modificar un contenido existente       |
| POST   | `/contenido`        | Crear un nuevo contenido                |
| DELETE | `/contenido/{id}`   | Eliminar un contenido por ID            |

### API de Curso

| Método | Endpoint           | Descripción                             |
|--------|--------------------|-----------------------------------------|
| GET    | `/curso`            | Listar todos los cursos                |
| POST   | `/curso`            | Crear un nuevo curso                   |
| GET    | `/curso/{id}`       | Obtener un curso por ID                |
