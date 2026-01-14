# 🏠 RealEstate-Pro Connect

**Enterprise Property Management System (Concept Phase)**

RealEstate-Pro Connect es una plataforma **cloud-native** diseñada para la gestión de listados inmobiliarios, con un fuerte enfoque en **alto rendimiento**, **escalabilidad** y **patrones arquitectónicos modernos**.  
El proyecto funciona como un **escaparate técnico** para la construcción de un backend robusto utilizando las capacidades más recientes del ecosistema Java.

---

## 🚀 Stack Técnico Actual

- **Lenguaje:** Java 21  
  - Virtual Threads  
  - Records
- **Framework:** Spring Boot 3.x
- **Arquitectura:** Arquitectura Hexagonal (Puertos y Adaptadores)
- **Resiliencia:** Resilience4j  
  - Circuit Breaker implementado (`appraisalCB`)
- **Base de Datos:** PostgreSQL + Spring Data JPA
- **Gestión de Dependencias:** Maven
- **Contenedores:** Docker & Docker Compose

---

## 🏗️ Resumen Arquitectónico

El proyecto sigue los principios de la **Arquitectura Hexagonal**, permitiendo un desacoplamiento total entre la lógica de negocio y los detalles de infraestructura:

- **Capa de Dominio**
  - Entidades
  - Value Objects
  - Servicios de dominio
- **Capa de Aplicación**
  - Orquestación de casos de uso
  - Definición de puertos (interfaces)
- **Capa de Infraestructura**
  - Adaptadores REST
  - Repositorios JPA
  - Clientes de servicios externos

---

## 🛠️ Características Implementadas

- **Gestión de Listados**
  - Operaciones CRUD básicas para propiedades inmobiliarias.
- **Patrón Circuit Breaker**
  - Protección ante fallos de servicios externos.
  - Servicio de tasación simulado con Resilience4j.
- **Estrategia de Fallback Estático**
  - Garantiza la continuidad operativa cuando servicios externos no están disponibles.
  - Retorno de valores de negocio por defecto.

---

## 🗺️ Roadmap y Próximos Pasos (Planned)

El sistema se encuentra en evolución activa. Los siguientes módulos están en fase de planificación o desarrollo inicial:

### 1. Observabilidad y Monitoreo 🔍

- **Micrometer Tracing**
  - Trazabilidad distribuida entre servicios.
- **Prometheus & Grafana**
  - Dashboards en tiempo real:
    - Salud del sistema
    - Métricas del Circuit Breaker

### 2. Arquitectura Orientada a Eventos (EDA) 📨

- **Apache Kafka**
  - Comunicación asíncrona para flujos no críticos:
    - Emails de confirmación
    - Logs de auditoría
- **Transactional Outbox Pattern**
  - Consistencia entre base de datos y eventos publicados.

### 3. Persistencia Avanzada 💾

- **Optimización JPA**
  - Criteria API
  - Specifications
  - Resolución de problemas N+1 mediante Entity Graphs
- **Migraciones de Base de Datos**
  - Integración con Liquibase o Flyway

### 4. Calidad de Código (QA) 🧪

- **Tests Unitarios**
  - JUnit 5
  - Mockito
  - Cobertura completa del dominio
- **Tests de Integración**
  - Testcontainers
  - Validación con bases de datos reales

### 5. Expansión de Negocio 📈

- **Motor de Tasación Avanzado**
  - Evolución hacia precios dinámicos
  - Cálculo basado en:
    - Ubicación
    - Tendencias de mercado
    - Variables históricas

---

## 🚦 Primeros Pasos

### Requisitos

- JDK 21
- Maven 3.9+
- Docker & Docker Compose
