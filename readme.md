# Ecommerce Microservices (Spring Cloud) — Monorepo

Sistema e-commerce basado en microservicios con **Spring Boot**, **Eureka Discovery**, **Spring Cloud Gateway**, **Feign**, **Resilience4j** y **MySQL**.  
Incluye documentación **OpenAPI/Swagger** por servicio y documentación centralizada desde el **Gateway**.

---

## 🧩 Arquitectura

Servicios incluidos:

- **Eureka Server** (`eureka-sv`) → Service Discovery
- **API Gateway** (`gateway-service`) → Entry point + ruteo por paths
- **Product Service** (`product-service`) → Catálogo (2 instancias)
- **ShoppingCart Service** (`shoppingcart-service`) → Carrito
- **Sale Service** (`sale-service`) → Ventas
- **MySQL** (`mysql`) → Una instancia, múltiples schemas (una DB por servicio)

---

## ✅ Requisitos

- Java 17+ (recomendado 17/21)
- Maven
- Docker Desktop (para docker-compose)
- Postman (opcional)

---

## 📁 Estructura del proyecto

Ejemplo (raíz del repo):

Ecommerce/
docker-compose.yml
.env
mysql-init/
01-init.sql
eureka-sv/
ApiGateway/
product-service/
shoppingcart-service/
sale-service/


---

## 🔐 Variables de entorno

Este proyecto usa **un `.env` en la raíz** del monorepo para Docker Compose.

Crear `Ecommerce/.env`:

```env
DB_USERNAME=root
DB_PASSWORD=root
