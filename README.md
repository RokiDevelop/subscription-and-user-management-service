# Subscription and User Management Service

Микросервис для управления пользователями и их подписками на цифровые сервисы (YouTube Premium, VK Музыка, Netflix и другие).

## Стек технологий
- **Spring Boot 3**
- **Java 17**
- **PostgreSQL**
- **Liquibase**
- **Docker** и **docker-compose**
- **SLF4J + Logback (default)** 
- **OpenAPI (Swagger)** 

## OpenApi (Swagger)
- **/swagger-ui/index.html** 

## Основные API эндпоинты
- **POST /users** — создание пользователя
- **GET /users/{id}** — получение данных пользователя
- **PUT /users/{id}** — обновление данных пользователя
- **DELETE /users/{id}** — удаление пользователя
- **POST /users/{id}/subscriptions** — добавление подписки
- **GET /users/{id}/subscriptions** — получение подписок
- **DELETE /users/{id}/subscriptions/{sub_id}** — удаление подписки
- **GET /subscriptions/top** — ТОП-3 популярных подписок

## Запуск проекта

1. **Клонировать репозиторий:**
   ```bash
   git clone <URL>
   cd <projectDir>
   
2. **Запуск с Docker:** В директории с проектом выполните
   ```bash
    docker compose up --build
   